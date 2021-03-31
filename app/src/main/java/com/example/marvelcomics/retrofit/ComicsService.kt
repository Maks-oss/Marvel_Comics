package com.example.marvelcomics.retrofit

import com.example.marvelcomics.*
import com.example.marvelcomics.data.datahelper.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {
    @GET("comics")
    suspend fun getComics(
        @Query("title") title: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("ts") timeStamp: String = TS,
        @Query("hash") hash: String = HASH,
        @Query("offset") offset: Int
    ):Response<Comics>
}