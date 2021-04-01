package com.example.marvelcomics.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelcomics.database.entities.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoritesDAO
}