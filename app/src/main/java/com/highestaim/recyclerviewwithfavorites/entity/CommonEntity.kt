package com.highestaim.recyclerviewwithfavorites.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "common_entity")
data class CommonEntity  (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "email")
    val email: String?
)