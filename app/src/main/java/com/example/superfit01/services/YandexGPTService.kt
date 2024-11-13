package com.example.superfit01.services


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexGPTService {
    @GET("tocken")
    suspend fun calculateCalories(@Query("product") product: String): Response<CalorieResponse>
}

data class CalorieResponse(val calories: Int)
