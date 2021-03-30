package com.example.marvelcomics.data.datahelper

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)