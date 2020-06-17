package com.highestaim.recyclerviewwithfavorites.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highestaim.recyclerviewwithfavorites.db.AppDatabase
import com.highestaim.recyclerviewwithfavorites.entity.CommonEntity
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.service.InfoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRepository(private var service: InfoService,private var db : AppDatabase) {

    fun getComments(): LiveData<List<CommentsModel>> {
        val data = MutableLiveData<List<CommentsModel>>()

        service.getInfo().enqueue(object : Callback<List<CommentsModel>> {
            override fun onResponse(call: Call<List<CommentsModel>>, response: Response<List<CommentsModel>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<CommentsModel>>, t: Throwable) {
            }
        })
        return data
    }


    suspend fun getCommentsFromLocal() : LiveData<List<CommonEntity>>{
        val data = MutableLiveData<List<CommonEntity>>()

        withContext(CoroutineScope(IO).coroutineContext) {
            data.postValue(db.commonDao().getMainInfo())
        }

        return data
    }

    fun insertMainDataToDb(data : List<CommonEntity>) {
        CoroutineScope(IO).launch {
            db.commonDao().insertAll(data)
        }
    }
}