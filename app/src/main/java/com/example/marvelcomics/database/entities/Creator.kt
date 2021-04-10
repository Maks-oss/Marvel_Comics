package com.example.marvelcomics.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Creator (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val creatorFullName:String,
    val creatorImage:String,
    val creatorJob:String,
    val comicId:Int,
):Serializable