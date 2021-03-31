package com.example.marvelcomics.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomics.ServiceLocator
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.scope
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    var favorites: MutableLiveData<List<Favorite>> = MutableLiveData()

    init {
        scope.launch {
            favorites.postValue(ServiceLocator.provideRepository().getFavorites())
        }
    }

}