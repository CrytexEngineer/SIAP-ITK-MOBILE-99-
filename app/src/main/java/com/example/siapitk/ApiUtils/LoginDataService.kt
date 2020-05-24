package com.example.siapitk.ApiUtils


import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginDataService {

    @POST("login")
    @Multipart
    fun login(
        @Part("MA_Email") MA_Email: String,
        @Part("MA_PASSWORD") MA_PASSWORD: String,
        @Part("MA_IMEI") MA_IMEI: String
    ): Call<ApiResponse>

    fun logout() {
        // TODO: revoke authentication
    }

    @POST("password/create")
    @Multipart
    fun resetPassword(
        @Part("MA_Email") MA_Email: String
    ): Call<ApiResponse>

    @GET("profile/{id}")
    fun getUserProfile(@Path("id") id: Int): Call<ApiResponse>
}