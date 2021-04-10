package com.example.marvelcomics.data.datahelper.comics.creators

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)