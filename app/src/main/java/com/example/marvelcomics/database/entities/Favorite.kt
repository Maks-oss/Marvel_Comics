package com.example.marvelcomics.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
//TODO add new tables with relations to favorite
@Entity
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val image:String
)