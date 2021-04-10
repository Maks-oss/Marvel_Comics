package com.example.marvelcomics.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelcomics.database.entities.Creator

@Dao
interface CreatorsDAO {
    @Insert
    suspend fun insertCreator(creator: Creator)

    @Delete
    suspend fun deleteCreator(creator: Creator)

    @Query("SELECT * FROM Creator WHERE comicId =:comicId")
    suspend fun getCreatorsById(comicId: Int): List<Creator>


}