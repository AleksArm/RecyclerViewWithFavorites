package com.highestaim.recyclerviewwithfavorites.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highestaim.recyclerviewwithfavorites.model.CommentsModel
import com.highestaim.recyclerviewwithfavorites.service.InfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRepository(private var service: InfoService) {

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
}