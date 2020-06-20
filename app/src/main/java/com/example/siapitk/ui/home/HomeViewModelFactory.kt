package com.example.siapitk.ui.login


import RetrofitInstance
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siapitk.ApiUtils.KelasDataService
import com.example.siapitk.ApiUtils.KelasViewmodel
import com.example.siapitk.data.HomeRepository
import com.example.siapitk.data.remoteDataSource.KelasDataSource

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class HomeViewModelFactory(var application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KelasViewmodel::class.java)) {
            return KelasViewmodel(
                repository = HomeRepository(
                    dataSource = KelasDataSource(
                        kelasDataService =
                        RetrofitInstance.getRetrofitInstance().create(KelasDataService::class.java)
                    ),
                    application = application
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
