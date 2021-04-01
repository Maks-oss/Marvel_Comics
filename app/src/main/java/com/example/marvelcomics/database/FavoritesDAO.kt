package com.example.marvelcomics.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelcomics.database.entities.Favorite

@Dao
interface FavoritesDAO {
    @Insert
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorite")
    suspend fun getFavorites():List<Favorite>

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE title=:title AND image=:image")
    suspend fun getFavoriteByTitleAndImage(title:String, image:String): Favorite
}