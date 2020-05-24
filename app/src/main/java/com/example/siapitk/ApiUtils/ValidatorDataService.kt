package com.example.siapitk.ApiUtils

import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.QRcode
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ValidatorDataService {


    @POST("validate")
    fun getValidation(@Body qrCode: QRcode): Call<ApiResponse>

    @Multipart
    @POST("validate/register_meeting")
    fun registerMeeting(
        @Part("MA_Nrp") MA_Nrp: Int,
        @Part("PT_Token") PT_Token: String
    ): Call<ApiResponse>
}