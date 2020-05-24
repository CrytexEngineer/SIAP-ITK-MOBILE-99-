package com.example.siapitk.data

interface LocalDataCallback {
    fun onSuccess(data: Any)
    fun onFailed(errorMessage: String?)
}