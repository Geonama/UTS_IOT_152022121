package com.example.uts_iot.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {

    // Ganti localhost dengan IP yang sesuai (10.0.2.2 untuk emulator, atau IP lokal untuk perangkat fisik)
    private const val BASE_URL = "http://10.0.2.2/api_iot/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}
