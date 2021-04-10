package com.example.marvelcomics.repository

import com.example.marvelcomics.data.datahelper.comics.creators.Result
import com.example.marvelcomics.database.dao.CreatorsDAO
import com.example.marvelcomics.database.entities.Creator
import com.example.marvelcomics.retrofit.ComicsService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class CreatorsRepository @Inject constructor(
    private val comicsService: ComicsService,
    private val creatorsDAO: CreatorsDAO
) {
    suspend fun getCreators(comicsId: Int): List<Result>? =
        comicsService.getCreators(comicsId = comicsId).body()!!.data.results

    suspend fun insertCreator(creator: Creator) = creatorsDAO.insertCreator(creator)
    suspend fun deleteCreator(creator: Creator) = creatorsDAO.deleteCreator(creator)

    suspend fun getCreatorsById(id: Int) = creatorsDAO.getCreatorsById(id)

}
