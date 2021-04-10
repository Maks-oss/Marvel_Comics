package com.example.marvelcomics.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val comicsRepository: ComicsRepository) :
    ViewModel() {
    val favorites: MutableLiveData<List<Favorite>> by lazy {
        MutableLiveData<List<Favorite>>().also {
            viewModelScope.launch {
                it.postValue(comicsRepository.getFavorites())
            }
        }
    }

    fun sortInDescending(currentList: List<Favorite>) {
        val list = mutableListOf<Favorite>().also {
            it.addAll(currentList)
        }
        list.sortByDescending { it.id }
        favorites.value = list
    }

    fun removeItem(item: Favorite) {
        viewModelScope.launch {
            comicsRepository.deleteFavorite(item)
            favorites.postValue(
                comicsRepository.getFavorites()
            )
        }
    }

    fun sortInAscending(currentList: List<Favorite>) {
        val list = mutableListOf<Favorite>().also {
            it.addAll(currentList)
        }
        list.sortBy { it.id }
        favorites.value = list
    }

    suspend fun getFavoritesAndCreators(item: Favorite) = comicsRepository.getFavoritesAndCreators()
        .find { it.favorite.comicId == item.comicId }
}