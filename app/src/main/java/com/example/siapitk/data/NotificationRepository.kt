package com.example.siapitk.data


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import com.example.siapitk.data.localDataSource.NotificationDao
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.remoteDataSource.NotificationDataSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationRepository(
    val dataSource: NotificationDataSource,
   val application: Application,
    val dao: NotificationDao
) {
    fun getRemoteNotification(MA_Nrp: Int, callback: RemoteDataCallback) {
        dataSource.getNotification(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                callback.onSuccess(data)

                GlobalScope.launch {
                    data.notification?.get(0)?.let { insert(it) }
                }
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }
        })

    }

    suspend fun insert(notification: com.example.siapitk.data.model.Notification) {
        dao.insert(notification)

    }


    fun getSavedNotification(): LiveData<List<com.example.siapitk.data.model.Notification>> {
        return dao.getAllNotification()
    }
}
