package com.example.marvelcomics.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.databinding.FavoritesListItemBinding
import com.example.marvelcomics.lists.viewholders.FavoritesViewHolder
import com.example.marvelcomics.scope
import com.example.marvelcomics.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.launch

class ListAdapter(private val favoritesViewModel: FavoritesViewModel) :
    ListAdapter<Favorite, FavoritesViewHolder>(
        AsyncDifferConfig.Builder<Favorite>(object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }).build()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            FavoritesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), this
        )

    fun removeItem(item: Favorite) {
        scope.launch {
            favoritesViewModel.favoritesRepository.deleteFavorite(item)
            favoritesViewModel.favorites.postValue(
                favoritesViewModel.favoritesRepository.getFavorites()
            )
        }
    }
    fun sortInDescending() {
        val list = mutableListOf<Favorite>().also {
            it.addAll(currentList)
        }
        list.sortByDescending { it.id }
        favoritesViewModel.favorites.value=list
    }

    fun sortInAscending() {
        val list = mutableListOf<Favorite>().also {
            it.addAll(currentList)
        }
        list.sortBy { it.id }
        favoritesViewModel.favorites.value=list
    }
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}