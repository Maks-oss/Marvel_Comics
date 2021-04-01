package com.example.marvelcomics.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcomics.data.datahelper.Result
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.lists.pagingsource.PostDataSource
import com.example.marvelcomics.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel() {
    var comicsPagingFlow: Flow<PagingData<Result>>? = null
    fun applyFlow(title: String) {
        comicsPagingFlow = Pager(PagingConfig(pageSize = 6)) {
            PostDataSource(
                comicsRepository,
                title,
            )
        }.flow.cachedIn(viewModelScope)
    }

    suspend fun getFavorite(title: String, image: String) =
        comicsRepository.getFavoriteByTitleAndImage(title, image)


    suspend fun isFavoriteExist(favorite: Favorite) =
        comicsRepository.getFavoriteByTitleAndImage(
            favorite.title,
            favorite.image
        ) != null


    suspend fun insertIntoFavorites(favorite: Favorite) =
            comicsRepository.insertFavorite(favorite)


    suspend fun deleteFromFavorite(favorite: Favorite) =
            comicsRepository.deleteFavorite(favorite)


}