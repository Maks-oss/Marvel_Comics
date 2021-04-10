package com.example.marvelcomics.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//TODO add new tables with relations to favorite
@Entity/*(foreignKeys = [
    ForeignKey(entity = Creator::class,
        parentColumns = ["comicId"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )])*/
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    val title:String,
    val image:String,
    val publicationDate:String,
    val comicId:Int
):Serializable