package com.example.siapitk.data


import android.app.Application
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.QRcode
import com.example.siapitk.data.remoteDataSource.ValidationDataSource


class QRScannerRepository(var dataSource: ValidationDataSource, application: Application) {


    fun getToken(qrCode: QRcode, remoteDataCallback: RemoteDataCallback) {

        dataSource.getToken(qrCode, object : RemoteDataCallback {

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

    fun registerMeeting( MA_Nrp: Int, PT_Token: String, remoteDataCallback: RemoteDataCallback) {

        dataSource.registerMeeting(MA_Nrp,PT_Token, object : RemoteDataCallback {

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