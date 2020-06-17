package com.highestaim.recyclerviewwithfavorites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highestaim.recyclerviewwithfavorites.entity.CommonEntity
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.repository.CommentsRepository
import kotlinx.coroutines.launch

class CommentsViewModel(private val repo : CommentsRepository) : ViewModel() {

    fun getComments(): LiveData<List<CommentsModel>> {
        return repo.getComments()
    }

    suspend fun getMainDataFromLocal() : LiveData<List<CommonEntity>> {
        return repo.getCommentsFromLocal()
    }

    fun insertMainDataToDb(data : List<CommonEntity>) {
        repo.insertMainDataToDb(data)
    }
}