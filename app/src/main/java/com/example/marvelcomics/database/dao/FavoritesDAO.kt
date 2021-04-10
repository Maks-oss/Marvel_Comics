package com.example.marvelcomics.database.dao

import androidx.room.*
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.database.entities.FavoriteAndCreators

@Dao
interface FavoritesDAO {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    suspend fun getFavorites(): List<Favorite>

    @Delete
    suspend fun delete(favorite: Favorite)


    @Query("SELECT * FROM favorite WHERE comicId=:id")
    suspend fun getFavoriteById(id: Int): Favorite

    @Transaction
    @Query("SELECT * FROM favorite")
    suspend fun getFavoritesAndCreators(): List<FavoriteAndCreators>



}