package com.example.marvelcomics.di

import android.app.Application
import androidx.room.Room
import com.example.marvelcomics.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "Favorites"
        ).build()
    }
}