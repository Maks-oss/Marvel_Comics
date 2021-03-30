package com.example.marvelcomics.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelcomics.repository.ComicsRepository
import com.example.marvelcomics.data.Result

class PostDataSource(private val comicsRepository: ComicsRepository, private val title:String):
    PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = comicsRepository.getResults(title)!!

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}