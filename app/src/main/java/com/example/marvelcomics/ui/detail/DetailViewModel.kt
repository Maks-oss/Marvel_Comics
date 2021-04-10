package com.example.marvelcomics.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomics.database.entities.Creator

class DetailViewModel :
    ViewModel() {
    var creatorsData: MutableLiveData<List<Creator>?> = MutableLiveData()
    fun initCreators(creators: List<Creator>?) {
        creatorsData.value = creators
    }
}