package com.example.marvelcomics.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.repository.FavoritesRepository
import com.example.marvelcomics.scope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(val favoritesRepository: FavoritesRepository) : ViewModel() {
    var favorites: MutableLiveData<List<Favorite>> = MutableLiveData()
    init {
        scope.launch {
            favorites.postValue(favoritesRepository.getFavorites())
        }
    }

}