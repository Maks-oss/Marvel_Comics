package com.example.marvelcomics.lists.listeners

import android.view.View
import android.widget.ImageButton
import com.example.marvelcomics.*
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.database.entities.FavoriteAndCreators
import com.example.marvelcomics.ui.search.SearchViewModel
import kotlinx.coroutines.launch

class FavoritesButtonListener(
    private val searchViewModel: SearchViewModel,
    private val message: ((String) -> Unit)? = null
) : View.OnClickListener, BaseScope {
    var isClicked = false
    var item: FavoriteAndCreators? = null

    fun applyItem(res:Result)=scope.launch {
        item = FavoriteAndCreators(
            getFavoriteFromResponseToDao(res), getCreatorsFromResponseToDao(
                res,
                res.id,
                searchViewModel.getCreatorsResponse(res.id)
            )
        )
    }


    override fun onClick(v: View?) {
        isClicked = !isClicked
        scope.launch {
            if (isClicked) {
                if (searchViewModel.isFavoriteExist(item!!.favorite)) {
                    toMain { message!!("The comic is already in the favorites") }
                } else {
                    toMain { (v as ImageButton).setFavoritesImage(isClicked) }
                    searchViewModel.insertIntoDatabase(item!!)
                }
            } else {
                toMain { (v as ImageButton).setFavoritesImage(isClicked) }
                searchViewModel.deleteFromDatabase(item!!.favorite)
            }
        }
    }

}