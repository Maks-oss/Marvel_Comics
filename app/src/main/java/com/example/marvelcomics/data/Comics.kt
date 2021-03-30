package com.example.marvelcomics.data

import com.example.marvelcomics.data.datahelper.Data

data class Comics(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)