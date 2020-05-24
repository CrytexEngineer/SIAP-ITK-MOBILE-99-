package com.example.siapitk.data.remoteDataSource


import android.util.Log
import com.example.siapitk.ApiUtils.PresensenceDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenceDataSource(val dataService: PresensenceDataService) {

    fun getPresences(MA_Nrp: Int, MK_ID: String, callback: RemoteDataCallback) {
        val call: Call<ApiResponse> = dataService.getPresences(MA_Nrp,MK_ID)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                response?.body()?.let { callback.onSuccess(it)
                    Log.d("RESPONSE", response?.body()?.properties.toString())}
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("EROR", t.message)
                callback.onFailed(t.message)
            }
        }
        call.enqueue(obj)
    }

    fun getPresenceCount(MA_Nrp: Int, MK_ID: String, callback: RemoteDataCallback) {
        val call: Call<ApiResponse> = dataService.getPresenceCount(MA_Nrp,MK_ID)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                response?.body()?.let { callback.onSuccess(it)
                    Log.d("RESPONSE", response?.body()?.properties.toString())}
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("EROR", t.message)
                callback.onFailed(t.message)
            }
        }
        call.enqueue(obj)
    }
}