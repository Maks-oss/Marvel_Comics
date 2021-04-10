package com.example.marvelcomics.retrofit

import com.example.marvelcomics.API_KEY
import com.example.marvelcomics.HASH
import com.example.marvelcomics.TS
import com.example.marvelcomics.data.datahelper.comics.comics.Comics
import com.example.marvelcomics.data.datahelper.comics.creators.Creators
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("comics/{comicsId}/creators")
    suspend fun getCreators(
        @Path("comicsId")comicsId:Int,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("ts") timeStamp: String = TS,
        @Query("hash") hash: String = HASH
    ):Response<Creators>
}