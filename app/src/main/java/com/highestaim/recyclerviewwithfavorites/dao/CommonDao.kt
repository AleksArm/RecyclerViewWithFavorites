package com.highestaim.recyclerviewwithfavorites.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.highestaim.recyclerviewwithfavorites.entity.CommonEntity

@Dao
interface CommonDao {

    @Query("SELECT * from common_entity ")
    fun getMainInfo(): List<CommonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(info : List<CommonEntity>)
}