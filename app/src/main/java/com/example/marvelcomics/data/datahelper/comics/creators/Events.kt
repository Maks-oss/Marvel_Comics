package com.example.marvelcomics.data.datahelper.comics.creators

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)