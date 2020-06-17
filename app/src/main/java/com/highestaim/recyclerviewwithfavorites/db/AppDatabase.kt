package com.highestaim.recyclerviewwithfavorites.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.highestaim.recyclerviewwithfavorites.dao.CommonDao
import com.highestaim.recyclerviewwithfavorites.dao.FavoriteDao
import com.highestaim.recyclerviewwithfavorites.entity.CommonEntity
import com.highestaim.recyclerviewwithfavorites.entity.FavoriteEntity


@Database(entities = [FavoriteEntity::class,CommonEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    abstract fun commonDao(): CommonDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}