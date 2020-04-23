package com.highestaim.recyclerviewwithfavorites.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity
import com.highestaim.recyclerviewwithfavorites.repository.FavoriteRepository

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {


    fun getFavorites(): LiveData<List<FavoriteEntity>>? {
        return favoriteRepository.allFavorites
    }

    fun insert(favoriteEntity: FavoriteEntity) {
        favoriteRepository.insert(favoriteEntity)
    }

    fun delete(favoriteEntity: FavoriteEntity) {
        favoriteRepository.delete(favoriteEntity)
    }

}