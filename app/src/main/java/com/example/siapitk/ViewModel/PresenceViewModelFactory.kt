package com.example.siapitk.ViewModel


import RetrofitInstance

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siapitk.ApiUtils.PresenceViewModel
import com.example.siapitk.ApiUtils.PresensenceDataService
import com.example.siapitk.data.PresenceRepository
import com.example.siapitk.data.QRScannerRepository
import com.example.siapitk.data.remoteDataSource.PresenceDataSource


class PresenceViewModelFactory(var application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PresenceViewModel::class.java)) {
            return PresenceViewModel(
                repository = PresenceRepository(
                    dataSource = PresenceDataSource(
                        dataService =
                        RetrofitInstance.getRetrofitInstance()
                            .create(PresensenceDataService::class.java)
                    ),
                    application = application
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}