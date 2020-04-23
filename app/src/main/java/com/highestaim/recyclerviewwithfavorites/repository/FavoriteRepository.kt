package com.highestaim.recyclerviewwithfavorites.repository

import androidx.lifecycle.LiveData
import com.highestaim.recyclerviewwithfavorites.db.AppDatabase
import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity

class FavoriteRepository(appDatabase: AppDatabase)  {

    private var favoriteDao = appDatabase.favoriteDao()

    val allFavorites: LiveData<List<FavoriteEntity>> = favoriteDao.getInfo()

    fun insert(favorite: FavoriteEntity) {
        favoriteDao.insert(favorite)
    }

    fun delete(favorite: FavoriteEntity) {
        favoriteDao.delete(favorite)
    }
}