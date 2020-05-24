package com.example.siapitk.data

import android.app.Application
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.remoteDataSource.KelasDataSource

class HomeRepository(var dataSource: KelasDataSource,application: Application) {


    fun getKelas(MA_Nrp: Int, remoteDataCallback: RemoteDataCallback) {

        dataSource.getKelas(MA_Nrp, object : RemoteDataCallback {

            override fun onSuccess(data: ApiResponse) {
                data?.let {
                    remoteDataCallback.onSuccess(it)
                }
            }

            override fun onFailed(errorMessage: String?) {
                remoteDataCallback.onFailed(errorMessage)
            }
        })
    }
}