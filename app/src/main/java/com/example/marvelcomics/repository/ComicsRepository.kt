package com.example.marvelcomics.repository

import com.example.marvelcomics.data.datahelper.Result
import com.example.marvelcomics.database.FavoritesDAO
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.retrofit.ComicsService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject
@Module
@InstallIn(ActivityRetainedComponent::class)
class ComicsRepository  @Inject constructor(
    private val comicsService: ComicsService,
    private val favoritesDAO: FavoritesDAO
) {
    suspend fun getResults(title: String, offset: Int): List<Result>? =
        comicsService.getComics(title = title, offset = offset).body()!!.data.results

    suspend fun getFavorites() = favoritesDAO.getFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = favoritesDAO.delete(favorite)
    suspend fun insertFavorite(favorite: Favorite) = favoritesDAO.insert(favorite)
    suspend fun getFavoriteByTitleAndImage(title:String, image:String) = favoritesDAO.getFavoriteByTitleAndImage(title,image)


}