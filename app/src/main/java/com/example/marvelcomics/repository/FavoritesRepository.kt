package com.example.marvelcomics.repository

import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.database.FavoritesDAO

/*@Module
@InstallIn(ActivityRetainedComponent::class)*/
class FavoritesRepository /*@Inject constructor*/(private val favoritesDAO: FavoritesDAO) {
    suspend fun getFavorites() = favoritesDAO.getFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = favoritesDAO.delete(favorite)
    suspend fun insertFavorite(favorite: Favorite) = favoritesDAO.insert(favorite)
    suspend fun getFavoriteByTitleAndImage(title:String, image:String) = favoritesDAO.getFavoriteByTitleAndImage(title,image)
}