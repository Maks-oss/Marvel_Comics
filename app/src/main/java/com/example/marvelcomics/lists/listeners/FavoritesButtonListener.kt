package com.example.marvelcomics.lists.listeners

import android.view.View
import android.widget.ImageButton
import com.example.marvelcomics.BaseScope
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.getFavoriteFromResponseToDao
import com.example.marvelcomics.setFavoritesImage
import com.example.marvelcomics.toMain
import com.example.marvelcomics.ui.search.SearchViewModel
import kotlinx.coroutines.launch

class FavoritesButtonListener(
    private val searchViewModel: SearchViewModel,
    private val message: ((String) -> Unit)?
) : View.OnClickListener, BaseScope {
    private var isClicked = false
    private var item: Result? = null


    fun setItem(item: Result) {
        this.item = item
    }

    fun getState() = isClicked

    override fun onClick(v: View?) {
        isClicked = !isClicked
        scope.launch {
            if (isClicked) {
                val favorite =
                    getFavoriteFromResponseToDao(item!!)
                if (searchViewModel.isFavoriteExist(favorite)) {
                    toMain { message!!("The comic is already in the favorites") }
                } else {
                    toMain { (v as ImageButton).setFavoritesImage(isClicked) }
                    searchViewModel.insertIntoDatabase(favorite, item!!)
                }
            } else {
                toMain { (v as ImageButton).setFavoritesImage(isClicked) }
                searchViewModel.deleteFromDatabase(item!!)
            }
        }
    }

}