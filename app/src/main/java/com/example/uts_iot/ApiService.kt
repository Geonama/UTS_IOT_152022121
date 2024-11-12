package com.example.uts_iot.api

import com.example.uts_iot.model.SuhuResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("backend.php") // Ganti dengan path yang benar ke PHP backend
    fun getSuhuData(): Call<SuhuResponse>
}
