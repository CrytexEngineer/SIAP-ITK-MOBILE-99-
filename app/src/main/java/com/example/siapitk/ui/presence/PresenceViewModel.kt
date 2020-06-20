package com.example.siapitk.ApiUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapitk.data.PresenceRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse


class PresenceViewModel(val repository: PresenceRepository) : ViewModel() {

    val presence = MutableLiveData<ApiResponse>()
    val presenceCount = MutableLiveData<ApiResponse>()
    val errorMessege = MutableLiveData<String>()

    fun getPresenceCount(MA_Nrp: Int, MK_ID: String) {

        repository.getPresenceCount(MA_Nrp, MK_ID, object : RemoteDataCallback {

            override fun onSuccess(data: ApiResponse) {
                data?.let {
                    presenceCount.value = it
                }
            }

            override fun onFailed(errorMessage: String?) {
                errorMessege.value = errorMessage
            }
        })
    }

    fun getPresences(MA_Nrp: Int, MK_ID: String) {

        repository.getPresences(MA_Nrp, MK_ID, object : RemoteDataCallback {

            override fun onSuccess(data: ApiResponse) {
                data?.let {
                    presence.value = it
                }
            }

            override fun onFailed(errorMessage: String?) {
                errorMessege.value = errorMessage
            }
        })
    }


}

