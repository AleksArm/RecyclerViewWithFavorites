package com.highestaim.recyclerviewwithfavorites.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClass {

    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    val retroInstance: Retrofit
        get() {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
}