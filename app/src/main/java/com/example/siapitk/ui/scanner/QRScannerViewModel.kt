package com.example.siapitk.ui.scanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapitk.data.QRScannerRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.QRcode


class QRScannerViewModel(var repository: QRScannerRepository) : ViewModel() {

    val validationToken = MutableLiveData<ApiResponse>()
    val registerMeetingStatus = MutableLiveData<ApiResponse>()
    val errorMessege=MutableLiveData<String>()

    fun getValidation(qrCode: QRcode) {
        repository.getToken(qrCode, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
           validationToken.value=data
            }

            override fun onFailed(errorMessage: String?) {
                errorMessege.value=errorMessage
            }
        })
    }

    fun registerMeeting(MA_Nrp: Int, PT_Token: String) {
        repository.registerMeeting(MA_Nrp, PT_Token, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
               registerMeetingStatus.value=data
            }

            override fun onFailed(errorMessage: String?) {
                TODO("Not yet implemented")
            }
        })
    }

}