package com.example.siapitk.data.remoteDataSource


import RetrofitInstance
import android.util.Log
import android.util.Log.d
import com.example.siapitk.ApiUtils.LoginDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class LoginDataSource(val loginDataService: LoginDataService) {


    fun login(
        username: String,
        password: String,
        imei: String,
        callback: RemoteDataCallback
    ) {


        val loginDataService =
            RetrofitInstance.getRetrofitInstance().create(LoginDataService::class.java)

        try {
            val call: Call<ApiResponse> = loginDataService.login(username, password, imei)
            val obj = object : Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>?, response: Response<ApiResponse>?
                ) {
//                    mLoggedInLoggedInUser.value = response?.body()?.loggedInUser
                    Log.d("RESPONSE", response?.body()?.properties.toString())
                    response?.body()?.let { callback.onSuccess(it) }
                }

                override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                    d("EROR", t.toString())
                    callback.onFailed(t?.message)
                }
            }
            call.enqueue(obj)

        } catch (e: Throwable) {
            d("EROR", e.toString())
            callback.onFailed(e.message)
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun ResetPassword(email: String, callback: RemoteDataCallback) {
        d("CALLED", "CALLED")
        val call: Call<ApiResponse> = loginDataService.resetPassword(email)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>?, response: Response<ApiResponse>?
            ) {
                Log.d("RESPONSE", response?.body()?.messege.toString())
                response?.body()?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                d("EROR", t.toString())

            }
        }
        call.enqueue(obj)
    }

    fun getUserProfile(MA_Nrp: Int, remoteDataCallback: RemoteDataCallback) {
        val call: Call<ApiResponse> = loginDataService.getUserProfile(MA_Nrp)
        val obj = object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                Log.d("RESPONSE", response?.body()?.properties.toString())
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                d("EROR", t.toString())
            }
        }
        call.enqueue(obj)


    }
}



