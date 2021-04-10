package com.example.marvelcomics.data.datahelper.comics.comics

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)