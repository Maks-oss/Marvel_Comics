package com.example.marvelcomics.lists.viewholders

import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.marvelcomics.BaseScope
import com.example.marvelcomics.GlideApp
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.databinding.FavoritesListItemBinding
import com.example.marvelcomics.toMain
import com.example.marvelcomics.ui.favorites.FavoritesFragmentDirections
import com.example.marvelcomics.ui.favorites.FavoritesViewModel
import kotlinx.coroutines.launch

class FavoritesViewHolder(
    private val favoritesListItemBinding: FavoritesListItemBinding,
    private val favoritesViewModel: FavoritesViewModel,
    private val delete: (Favorite) -> Unit
) :
    RecyclerView.ViewHolder(
        favoritesListItemBinding.root
    ), BaseScope {

    fun bind(item: Favorite) =
        favoritesListItemBinding.apply {
            GlideApp.with(root)
                .load(item.image)
                .apply(RequestOptions.circleCropTransform()).transition(DrawableTransitionOptions.withCrossFade())
                .into(favoritesImage)
            favoritesTitle.text = item.title
            favoritesImage.transitionName = item.image
            favoritesRoot.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    favoritesImage to item.image
                )
                scope.launch {
                    val action =
                        FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                            true,
                            favoritesViewModel.getFavoritesAndCreators(item)
                        )
                    toMain { it.findNavController().navigate(action, extras) }
                }
            }
            deleteButton.setOnClickListener { delete(item) }
        }
}