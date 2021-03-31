package com.example.marvelcomics.lists.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.databinding.FavoritesListItemBinding
import com.example.marvelcomics.lists.adapters.ListAdapter

class FavoritesViewHolder(
    private val favoritesListItemBinding: FavoritesListItemBinding,
    private val adapter: ListAdapter
) :
    RecyclerView.ViewHolder(
        favoritesListItemBinding.root
    ) {

    fun bind(item: Favorite) =
        favoritesListItemBinding.apply {
            Glide.with(root)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform())
                .into(favoritesImage)
            favoritesTitle.text = item.title
            deleteButton.setOnClickListener{
                adapter.removeItem(item)
            }
        }
}