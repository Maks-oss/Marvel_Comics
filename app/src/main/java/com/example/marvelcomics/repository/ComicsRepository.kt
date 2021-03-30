package com.example.marvelcomics.repository

import android.util.Log
import com.example.marvelcomics.data.Result
import com.example.marvelcomics.retrofit.ComicsService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject
@Module
@InstallIn(ActivityRetainedComponent::class)
class ComicsRepository @Inject constructor(
    private val comicsService: ComicsService,
)  {
    suspend fun getResults(title:String): List<Result>? {
        val comics = comicsService.getComics(title = title)
        Log.d("TAG", "receiveComic: ${comics.raw().request.url}")
        return comics.body()!!.data.results
    }

}