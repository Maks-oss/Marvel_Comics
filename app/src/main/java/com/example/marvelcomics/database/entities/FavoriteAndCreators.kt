package com.example.marvelcomics.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class FavoriteAndCreators (
    @Embedded val favorite: Favorite,
    @Relation(parentColumn = "id",entityColumn = "comicId")
    val creators: List<Creator>
):Serializable