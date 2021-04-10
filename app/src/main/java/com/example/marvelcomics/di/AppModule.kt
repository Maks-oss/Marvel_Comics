package com.example.marvelcomics.di

import android.content.Context
import androidx.room.Room
import com.example.marvelcomics.database.AppDatabase
import com.example.marvelcomics.database.dao.CreatorsDAO
import com.example.marvelcomics.database.dao.FavoritesDAO
import com.example.marvelcomics.retrofit.ComicsService
import com.example.marvelcomics.retrofit.Common
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideComicsService() :ComicsService{
        return Common.retrofitService
    }


    @InstallIn(SingletonComponent::class)
    @Module
    class DatabaseModule {
        @Provides
        fun provideChannelDao(appDatabase: AppDatabase): FavoritesDAO {
            return appDatabase.favoriteDao()
        }
        @Provides
        fun provideCreatorsDao(appDatabase: AppDatabase): CreatorsDAO {
            return appDatabase.creatorsDao()
        }
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Favorites"
        ).fallbackToDestructiveMigration().build()
    }

}