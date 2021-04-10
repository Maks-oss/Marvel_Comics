package com.example.marvelcomics

import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.database.entities.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.marvelcomics.data.datahelper.comics.creators.Result as CreatorResult

const val API_KEY = "5775c95c495d376e3747cd1199772bd4"
const val HASH = "7cdc7f2b3bad9a8ced7a59368f96b6d6"
const val TS = "1"

private fun animateView(view: View, translation: Float, alpha: Float, visibility: Int) =
    view.animate().translationY(translation).alpha(alpha)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = visibility
            }
        })

fun RecyclerView.addAnimationOnView(view: View) =
    addOnScrollListener(object :
        RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy < 0) {
                animateView(view, 0F, 1.0F, View.VISIBLE)
            } else if (dy > 0) {
                animateView(view, dy.toFloat(), 0.0F, View.GONE)
            }
        }

    })


interface BaseScope {
    val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
}

suspend fun <T : Any> toMain(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main, block)

fun Result.getImage() = images.first().path.plus(".jpg")
fun Result.getDate() =
    "Publication date: " + dates.find { it.type == "onsaleDate" }?.date?.substringBefore("T")

fun ImageButton.setFavoritesImage(state: Boolean) =
    if (state) {
        setImageResource(R.drawable.btn_star_big_on)
    } else {
        setImageResource(R.drawable.btn_star_big_off)
    }


fun getFavoriteFromResponseToDao(item: Result) = Favorite(
    title = item.title,
    image = item.getImage(),
    publicationDate = item.getDate(),
    comicId = item.id
)

fun getCreatorsFromResponseToDao(
    item: Result,
    id: Int,
    creators: List<CreatorResult>
): List<Creator> = mutableListOf<Creator>().also {
    creators.forEach { res ->
        it.add(
            Creator(
                creatorFullName = res.fullName,
                creatorImage = res.thumbnail.path.plus(".jpg"),
                creatorJob =
                item.creators.items.find { it.name == res.fullName }!!.role,
                comicId = id
            )
        )
    }
}




