package com.example.marvelcomics.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.repository.ComicsRepository
import com.example.marvelcomics.repository.CreatorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository,
    private val creatorsRepository: CreatorsRepository
) :
    ViewModel() {
    val favorites: MutableLiveData<List<Favorite>> = MutableLiveData()

    fun applyLiveData() = viewModelScope.launch {  favorites.postValue(comicsRepository.getFavorites())}
    fun sortInDescending(currentList: List<Favorite>) {
        val list = mutableListOf<Favorite>().also {
            it.addAll(currentList)
        }
        list.sortByDescending { it.id }
        favorites.value = list
    }

    fun removeItem(item: Favorite) {
        viewModelScope.launch {
            creatorsRepository.getCreatorsById(comicsRepository.getFavorite(item.comicId).id)
                .forEach {
                    creatorsRepository.deleteCreator(it)
                }
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