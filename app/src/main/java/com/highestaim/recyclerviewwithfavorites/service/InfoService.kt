package com.highestaim.recyclerviewwithfavorites.service

import com.highestaim.recyclerviewwithfavorites.model.Model
import com.highestaim.recyclerviewwithfavorites.remote.RetroClass
import retrofit2.Call
import retrofit2.http.GET

interface InfoService {

    @GET("/comments")
    fun getInfo(): Call<List<Model>>

    companion object {
        fun create(): InfoService {
            return RetroClass.retroInstance.create(InfoService::class.java)
        }
    }
}