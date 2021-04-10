package com.example.marvelcomics.data.datahelper.comics.creators

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)