package com.example.superfit.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json", "Authorization: Bearer Bsb62mJk18902Z")
    @POST("v1/calculateCalories")
    fun fetchCalories(@Body request: CalorieRequest): Call<CalorieResponse>
}

data class CalorieRequest(
    val dishName: String
)

data class CalorieResponse(
    val calories: Int
)