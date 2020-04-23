package com.highestaim.recyclerviewwithfavorites.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highestaim.recyclerviewwithfavorites.model.Model
import com.highestaim.recyclerviewwithfavorites.service.InfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoRepository(private var service: InfoService) {

    fun getInfo(): LiveData<List<Model>> {
        val data = MutableLiveData<List<Model>>()

        service.getInfo().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {

            }
        })
        return data
    }
}