package com.example.siapitk.ApiUtils


import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface PresensenceDataService {



    @GET("presence/index")
    fun getPresences(
        @Query("MA_Nrp") MA_Nrp: Int,
        @Query("MK_ID") MK_ID: String
    ): Call<ApiResponse>



    @GET("presence/count")
    fun getPresenceCount(
        @Query("MA_Nrp") MA_Nrp: Int,
        @Query("MK_ID") MK_ID: String
    ): Call<ApiResponse>
}