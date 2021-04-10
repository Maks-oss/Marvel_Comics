package com.example.marvelcomics.lists.viewholders

import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.*
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.database.entities.FavoriteAndCreators
import com.example.marvelcomics.databinding.ComicsListItemBinding
import com.example.marvelcomics.lists.listeners.FavoritesButtonListener
import com.example.marvelcomics.ui.search.SearchFragmentDirections
import com.example.marvelcomics.ui.search.SearchViewModel
import kotlinx.coroutines.launch

class ComicsViewHolder(
    private val comicsListItemBinding: ComicsListItemBinding,
    private val searchViewModel: SearchViewModel,
    message: (String) -> Unit
) :
    RecyclerView.ViewHolder(
        comicsListItemBinding.root
    ), BaseScope {
    private val favoritesButtonListener: FavoritesButtonListener =
        FavoritesButtonListener(searchViewModel, message)


    fun bind(item: Result) =
        comicsListItemBinding.apply{
            favoritesButtonListener.setItem(item)
            Glide.with(root)
                .load(item.getImage())
                .into(comicsImage)

            comicsTitle.text = item.title
            comicsDate.text = item.getDate()
            favoritesButton.setFavoritesImage(favoritesButtonListener.getState())

            comicsImage.transitionName = item.getImage()
            comicsRoot.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    comicsImage to item.getImage()
                )
                scope.launch {
                    val favorite= getFavoriteFromResponseToDao(item)
                    val creators = getCreatorsFromResponseToDao(
                        item,
                        favorite.id,
                        searchViewModel.getCreatorsResponse(item.id)
                    )
                    val action = SearchFragmentDirections.actionMainFragmentToDetailFragment(
                        favoritesButtonListener.getState(),
                        FavoriteAndCreators(favorite,creators)
                    )
                    toMain { it.findNavController().navigate(action, extras) }
                }

            }
            favoritesButton.setOnClickListener(favoritesButtonListener)

        }


}
