package com.example.marvelcomics.retrofit

object Common {
    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"
    val retrofitService: ComicsService=RetrofitClient.getClient(BASE_URL).create(ComicsService::class.java)

}