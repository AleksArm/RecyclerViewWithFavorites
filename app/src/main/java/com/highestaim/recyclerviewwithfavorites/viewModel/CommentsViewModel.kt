package com.highestaim.recyclerviewwithfavorites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.repository.CommentsRepository

class CommentsViewModel(private val repo : CommentsRepository) : ViewModel() {

    fun getComments(): LiveData<List<CommentsModel>> {
        return repo.getComments()
    }
}