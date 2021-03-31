package com.example.marvelcomics.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val image:String
)