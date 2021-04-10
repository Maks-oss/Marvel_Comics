package com.example.marvelcomics.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelcomics.database.dao.CreatorsDAO
import com.example.marvelcomics.database.dao.FavoritesDAO
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.database.entities.Favorite

@Database(entities = [Favorite::class,Creator::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoritesDAO
    abstract fun creatorsDao(): CreatorsDAO
}