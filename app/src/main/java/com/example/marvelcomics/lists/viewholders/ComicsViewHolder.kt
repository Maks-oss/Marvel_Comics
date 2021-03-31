package com.example.marvelcomics.lists.viewholders

import android.R
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcomics.ServiceLocator
import com.example.marvelcomics.data.datahelper.Result
import com.example.marvelcomics.database.Favorite
import com.example.marvelcomics.databinding.ComicsListItemBinding
import com.example.marvelcomics.message.ShowMessage
import com.example.marvelcomics.scope
import com.example.marvelcomics.toMain
import kotlinx.coroutines.launch

class ComicsViewHolder(
    private val comicsListItemBinding: ComicsListItemBinding,
    private val message: ShowMessage
) :
    RecyclerView.ViewHolder(
        comicsListItemBinding.root
    ) {
    private var isClicked = false
    private val provideRepository = ServiceLocator.provideRepository()
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
                        val favorite = Favorite(
                            title = item.title,
                            image = image
                        )
                        if (isFavoriteExist(favorite)) {
                            toMain { message.showMessage("The comic is already in the favorites") }
                        } else {
                            toMain { favoritesButton.setImageResource(R.drawable.btn_star_big_on) }
                            provideRepository.insertFavorite(favorite)
                        }
                    } else {
                        toMain { favoritesButton.setImageResource(R.drawable.btn_star_big_off) }
                        provideRepository.deleteFavorite(getFavorite(item.title,image))
                    }
                }
            }
        }
    }

    private suspend fun getFavorite(title: String,image:String) = provideRepository.getFavoriteByTitleAndImage(title,image)
    private suspend fun isFavoriteExist(favorite: Favorite) =
        provideRepository.getFavoriteByTitleAndImage(favorite.title,favorite.image) != null

}