package com.example.siapitk.ui.notification


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.siapitk.data.NotificationRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.Notification


class NotificationViewModel(
    private val repository: NotificationRepository,
    val application: Application
) : ViewModel() {
    init {

    }

    val remoteNotification = MutableLiveData<Notification>()
    val errorMessege = MutableLiveData<String>()


    fun getRemoteNotification(MA_Nrp: Int): MutableLiveData<Int> {
        val notificationCount = MutableLiveData<Int>()
        repository.getRemoteNotification(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                remoteNotification.value = data.notification?.get(0)
                var notificationPreference =
                    PreferenceManager.getDefaultSharedPreferences(application)
                var initialNotificationCount =
                    notificationPreference.getInt("NOTIFICATION_COUNT", 0)
                notificationPreference.edit()
                    .putInt("NOTIFICATION_COUNT", initialNotificationCount++)

                notificationCount.value = notificationPreference.getInt("NOTIFICATION_COUNT", 0)


            }

            override fun onFailed(errorMessage: String?) {
                errorMessege.value = errorMessage
            }

        })
        return notificationCount
    }

    fun getSavedNotification(): LiveData<List<Notification>> {
        var notification = MutableLiveData<ArrayList<Notification>>()
        return repository.getSavedNotification()
    }


}