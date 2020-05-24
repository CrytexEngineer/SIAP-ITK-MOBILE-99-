package com.example.siapitk.ViewModel

import RetrofitInstance
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siapitk.ApiUtils.NotificationDataService
import com.example.siapitk.data.NotificationRepository
import com.example.siapitk.data.localDataSource.NotificationRoomDatabase
import com.example.siapitk.data.remoteDataSource.NotificationDataSource
import com.example.siapitk.ui.notification.NotificationViewModel

class NotificationViewModelFactory(var application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(
                repository = NotificationRepository(
                    dataSource = NotificationDataSource(
                        dataService =
                        RetrofitInstance.getRetrofitInstance()
                            .create(NotificationDataService::class.java)
                    ),
                    application = application,
                    dao = NotificationRoomDatabase.getDatabase(application).notificationDao()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}