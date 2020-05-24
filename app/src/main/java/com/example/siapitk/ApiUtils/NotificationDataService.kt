package com.example.siapitk.ApiUtils

import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  NotificationDataService {
    @GET("notification")
    fun getNotification(
        @Query("MA_Nrp") MA_Nrp: Int
    ): Call<ApiResponse>
}