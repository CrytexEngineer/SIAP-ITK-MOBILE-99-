package com.example.siapitk.ApiUtils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapitk.data.HomeRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse


class KelasViewmodel(val repository: HomeRepository) : ViewModel() {

    val kelas = MutableLiveData<ApiResponse>()

    fun getKelas(MA_Nrp: Int) {
        repository.getKelas(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                kelas.value = data
            }

            override fun onFailed(errorMessage: String?) {
                Log.d("EROR_KELAS", errorMessage)
            }
        })
    }
}

