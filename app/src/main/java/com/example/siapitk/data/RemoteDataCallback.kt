package com.example.siapitk.data

import com.example.siapitk.data.model.ApiResponse


interface RemoteDataCallback {
        fun onSuccess(data: ApiResponse)
fun onFailed(errorMessage: String?)
}
