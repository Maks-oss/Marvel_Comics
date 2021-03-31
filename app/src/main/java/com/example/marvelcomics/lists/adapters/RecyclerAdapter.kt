package com.example.marvelcomics.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcomics.ServiceLocator
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.databinding.FavoritesListItemBinding
import com.example.marvelcomics.lists.viewholders.FavoritesViewHolder
import com.example.marvelcomics.scope
import com.example.marvelcomics.toMain
import kotlinx.coroutines.launch

class RecyclerAdapter : RecyclerView.Adapter<FavoritesViewHolder>() {
    private var list= mutableListOf<Favorite>()
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
            ServiceLocator.provideRepository().deleteFavorite(item)
            toMain {
                val indexOf = list.indexOf(item)
                list.remove(item)
                notifyItemRemoved(indexOf)
            }
        }
    }

    fun submitList(list: List<Favorite>) {
        this.list = list as MutableList<Favorite>
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =  list.size

}
