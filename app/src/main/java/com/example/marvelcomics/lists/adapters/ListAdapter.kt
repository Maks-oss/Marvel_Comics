package com.example.marvelcomics.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.databinding.FavoritesListItemBinding
import com.example.marvelcomics.lists.viewholders.FavoritesViewHolder

class ListAdapter(private val delete: (Favorite)->Unit) :
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
            ), delete
        )

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}