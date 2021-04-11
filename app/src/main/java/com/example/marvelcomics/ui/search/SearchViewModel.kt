package com.example.marvelcomics.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.database.entities.FavoriteAndCreators
import com.example.marvelcomics.lists.pagingsource.PostDataSource
import com.example.marvelcomics.repository.ComicsRepository
import com.example.marvelcomics.repository.CreatorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository,
    private val creatorsRepository: CreatorsRepository
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
    suspend fun getCreatorsResponse(id:Int) = withContext(Dispatchers.Default) {
        creatorsRepository.getCreators(
            id
        )?: emptyList()
    }
    suspend fun insertIntoDatabase(
        favoriteAndCreators: FavoriteAndCreators
    ) {
        favoriteAndCreators.apply {
            comicsRepository.insertFavorite(favorite)
            val id = comicsRepository.getFavorite(favorite.comicId).id
            creators.forEach {
                it.comicId=id
                creatorsRepository.insertCreator(it)
            }
        }

    }
    suspend fun isFavoriteExist(favorite: Favorite) =
        comicsRepository.getFavorite(favorite.comicId) != null

    suspend fun deleteFromDatabase(item: Favorite) {
        val favorite = comicsRepository.getFavorite(
            item.comicId
        )
        creatorsRepository.getCreatorsById(favorite.id).forEach {
           creatorsRepository.deleteCreator(it)
        }
        comicsRepository.deleteFavorite(
            favorite
        )
    }

}