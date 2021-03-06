package com.example.marvelcomics.lists.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelcomics.data.datahelper.comics.comics.Result
import com.example.marvelcomics.repository.ComicsRepository

class PostDataSource(
    private val comicsRepository: ComicsRepository,
    private val title: String
) :
    PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = comicsRepository.getResults(title,nextPageNumber)!!
            if(response.isNullOrEmpty()){
                throw Exception("Nothing was found")
            }
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 6
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}