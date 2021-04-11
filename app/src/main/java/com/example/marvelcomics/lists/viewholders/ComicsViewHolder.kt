package com.example.marvelcomics.lists.viewholders

import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.marvelcomics.GlideApp
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.databinding.ComicsListItemBinding
import com.example.marvelcomics.getDate
import com.example.marvelcomics.getImage
import com.example.marvelcomics.lists.listeners.FavoritesButtonListener
import com.example.marvelcomics.setFavoritesImage
import com.example.marvelcomics.ui.search.SearchFragmentDirections
import com.example.marvelcomics.ui.search.SearchViewModel

class ComicsViewHolder(
    private val comicsListItemBinding: ComicsListItemBinding,
    searchViewModel: SearchViewModel,
    message: (String) -> Unit
) :
    RecyclerView.ViewHolder(
        comicsListItemBinding.root
    ) {
    private val favoritesButtonListener: FavoritesButtonListener =
        FavoritesButtonListener(searchViewModel, message = message)


    fun bind(item: Result) =
        comicsListItemBinding.apply {

            GlideApp.with(root)
                .load(item.getImage()).transition(DrawableTransitionOptions.withCrossFade())
                .into(comicsImage)

            comicsTitle.text = item.title
            comicsDate.text = item.getDate()
            favoritesButton.setFavoritesImage(false)

            comicsImage.transitionName = item.getImage()

            favoritesButtonListener.applyItem(item)
            comicsRoot.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    comicsImage to item.getImage()
                )
                val action = SearchFragmentDirections.actionMainFragmentToDetailFragment(
                    favoritesButtonListener.isClicked,
                    favoritesButtonListener.item
                )
                it.findNavController().navigate(action, extras)


            }
            favoritesButton.setOnClickListener(favoritesButtonListener)

        }


}
