package com.example.siapitk.data.remoteDataSource


import android.util.Log
import com.example.siapitk.ApiUtils.KelasDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KelasDataSource(private val kelasDataService: KelasDataService) {
    fun getKelas(MA_Nrp: Int, remoteDataCallback: RemoteDataCallback) {

        val call: Call<ApiResponse> = kelasDataService.getKelas(MA_Nrp)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                response?.body()?.let { remoteDataCallback.onSuccess(it) }
                Log.d("RESPONSE", response?.body()?.properties.toString())
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                remoteDataCallback.onFailed(t?.message)
            }
        }
        call.enqueue(obj)
    }


}