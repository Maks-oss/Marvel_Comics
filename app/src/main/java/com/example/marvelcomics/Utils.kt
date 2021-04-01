package com.example.marvelcomics

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

fun RecyclerView.addAnimationOnView(view:View){
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
}
interface BaseScope{
    val scope: CoroutineScope
        get() = CoroutineScope(Dispatchers.IO)
}
suspend fun <T : Any> toMain(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main, block)

