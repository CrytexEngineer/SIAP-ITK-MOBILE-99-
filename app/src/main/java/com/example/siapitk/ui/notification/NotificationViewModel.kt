package com.example.siapitk.ui.notification


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapitk.data.LocalDataCallback
import com.example.siapitk.data.NotificationRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.Notification


class NotificationViewModel(private val repository: NotificationRepository) : ViewModel() {
    init {

    }

    val remoteNotification = MutableLiveData<Notification>()
    val errorMessege = MutableLiveData<String>()


    fun getRemoteNotification(MA_Nrp: Int) {
        repository.getRemoteNotification(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                remoteNotification.value = data.notification?.get(0)
            }

            override fun onFailed(errorMessage: String?) {
                errorMessege.value = errorMessage
            }
        })

    }

    fun getSavedNotification(): LiveData<List<Notification>> {
        var notification = MutableLiveData<ArrayList<Notification>>()
        return repository.getSavedNotification()
    }

}