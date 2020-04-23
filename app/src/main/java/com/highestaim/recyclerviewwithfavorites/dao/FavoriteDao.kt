package com.highestaim.recyclerviewwithfavorites.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity


@Dao
interface FavoriteDao {
    @Query("SELECT * from info_table ")
    fun getInfo(): LiveData<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteEntity: FavoriteEntity)

    @Delete()
    fun delete(favoriteEntity: FavoriteEntity)
}