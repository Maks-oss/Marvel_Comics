package com.example.marvelcomics.di

import com.example.marvelcomics.lists.adapters.RecyclerAdapter
import com.example.marvelcomics.retrofit.ComicsService
import com.example.marvelcomics.retrofit.Common
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    @Provides
    fun provideRecyclerAdapter() :RecyclerAdapter{
        return RecyclerAdapter()
    }
}