package com.example.marvelcomics

import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val API_KEY = "5775c95c495d376e3747cd1199772bd4"
const val HASH = "7cdc7f2b3bad9a8ced7a59368f96b6d6"
const val TS = "1"
val scope = CoroutineScope(Dispatchers.IO)

suspend fun <T : Any> toMain(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main, block)

