package com.highestaim.recyclerviewwithfavorites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.repository.InfoRepository

class InfoViewModel(private val repo : InfoRepository) : ViewModel() {

    fun getInfo(): LiveData<List<CommentsModel>> {
        return repo.getInfo()
    }
}