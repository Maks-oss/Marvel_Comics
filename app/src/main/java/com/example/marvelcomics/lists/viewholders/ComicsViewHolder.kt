package com.example.marvelcomics.lists.viewholders

import android.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.BaseScope
import com.example.marvelcomics.data.datahelper.Result
import com.example.marvelcomics.database.entities.Favorite
import com.example.marvelcomics.databinding.ComicsListItemBinding
import com.example.marvelcomics.toMain
import com.example.marvelcomics.ui.search.SearchViewModel
import kotlinx.coroutines.launch

class ComicsViewHolder(
    private val comicsListItemBinding: ComicsListItemBinding,
    private val searchViewModel: SearchViewModel,
    private val message: (String) -> Unit
) :
    RecyclerView.ViewHolder(
        comicsListItemBinding.root
    ),BaseScope {
    private var isClicked = false
    fun bind(item: Result) {
        comicsListItemBinding.apply {
            val image = item.images.first().path.plus(".jpg")
            val date =
                "Publication date: " + item.dates.find { it.type == "onsaleDate" }?.date?.substringBefore(
                    "T"
                )
            Glide.with(root)
                .load(image)
                .into(comicsImage)

            comicsTitle.text = item.title
            comicsDate.text = date
            favoritesButton.setImageResource(R.drawable.btn_star_big_off)
            favoritesButton.setOnClickListener {
                isClicked = !isClicked
                scope.launch {
                    if (isClicked) {
                        val favorite =
                            Favorite(
                                title = item.title,
                                image = image
                            )
                        if (searchViewModel.isFavoriteExist(favorite)) {
                            toMain { message("The comic is already in the favorites") }
                        } else {
                            toMain { favoritesButton.setImageResource(R.drawable.btn_star_big_on) }
                            searchViewModel.insertIntoFavorites(favorite)
                        }
                    } else {
                        toMain { favoritesButton.setImageResource(R.drawable.btn_star_big_off) }
                        searchViewModel.deleteFromFavorite(searchViewModel.getFavorite(item.title, image))
                    }
                }
            }
        }
    }

}