package com.example.siapitk.ui.scanner

import RetrofitInstance
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siapitk.ApiUtils.ValidatorDataService
import com.example.siapitk.data.QRScannerRepository
import com.example.siapitk.data.remoteDataSource.ValidationDataSource


class QRScannerViewModelFactory(var application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QRScannerViewModel::class.java)) {
            return QRScannerViewModel(
                repository = QRScannerRepository(
                    dataSource = ValidationDataSource(
                        validatorDataService =
                        RetrofitInstance.getRetrofitInstance()
                            .create(ValidatorDataService::class.java)
                    ),
                    application = application
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}