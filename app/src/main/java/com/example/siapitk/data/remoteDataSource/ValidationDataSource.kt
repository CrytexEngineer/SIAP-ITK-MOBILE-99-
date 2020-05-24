package com.example.siapitk.data.remoteDataSource

import android.util.Log
import com.example.siapitk.ApiUtils.ValidatorDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.QRcode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ValidationDataSource(val validatorDataService: ValidatorDataService) {


    fun getToken(qrCode: QRcode, remoteDataCallback: RemoteDataCallback) {
        val call: Call<ApiResponse> = validatorDataService.getValidation(qrCode)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                response?.body()?.let {
                    remoteDataCallback.onSuccess(it)
                    Log.d("RESPONSE", response?.body()?.properties.toString())
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("EROR", t.message)
                remoteDataCallback.onFailed(t.message)
            }
        }
        call.enqueue(obj)
    }

    fun registerMeeting(MA_Nrp: Int, PT_Token: String, remoteDataCallback: RemoteDataCallback) {
        val call: Call<ApiResponse> = validatorDataService.registerMeeting(MA_Nrp, PT_Token)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                Log.d("RESPONSE", response?.body()?.properties.toString())
                response?.body()?.let { remoteDataCallback.onSuccess(it) }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }

        }
        call.enqueue(obj)
    }

}
