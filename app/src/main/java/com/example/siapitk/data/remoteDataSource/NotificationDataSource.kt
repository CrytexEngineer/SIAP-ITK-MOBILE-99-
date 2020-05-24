package com.example.siapitk.data.remoteDataSource


import android.util.Log
import com.example.siapitk.ApiUtils.NotificationDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationDataSource( val dataService: NotificationDataService) {
    fun getNotification(MA_Nrp: Int, callback: RemoteDataCallback) {
        val call: Call<ApiResponse> = dataService.getNotification(MA_Nrp)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                response?.body()?.let { callback.onSuccess(it)
                    Log.d("RESPONSE", response?.body()?.notification?.get(0)?.notificationMsg)}
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("EROR", t.message)
                callback.onFailed(t.message)
            }
        }
        call.enqueue(obj)
    }
}