package com.example.siapitk.data


import android.app.Application
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.remoteDataSource.PresenceDataSource

class PresenceRepository(var dataSource: PresenceDataSource, application: Application) {


    fun getPresenceCount(MA_Nrp: Int, MK_ID: String, remoteDataCallback: RemoteDataCallback) {

        dataSource.getPresenceCount(MA_Nrp, MK_ID, object : RemoteDataCallback {

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

    fun getPresences(MA_Nrp: Int, MK_ID: String, remoteDataCallback: RemoteDataCallback) {

        dataSource.getPresences(MA_Nrp, MK_ID, object : RemoteDataCallback {

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