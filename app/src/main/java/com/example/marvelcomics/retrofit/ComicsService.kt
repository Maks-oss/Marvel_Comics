package com.example.marvelcomics.retrofit

import com.example.marvelcomics.*
import com.example.marvelcomics.data.Comics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {
    @GET("comics")
    suspend fun getComics(
        @Query("title") title: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("ts") timeStamp: String = TS,
        @Query("hash") hash: String = HASH
    ):Response<Comics>
}