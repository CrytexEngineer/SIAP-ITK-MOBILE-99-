package com.example.siapitk.data


import android.app.Application
import androidx.preference.PreferenceManager
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.LoggedInUser
import com.example.siapitk.data.remoteDataSource.LoginDataSource


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource, var application: Application) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, imei: String, callback: RemoteDataCallback) {
        // handle login

        dataSource.login(username, password, imei, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                callback.onSuccess(data)
                data.loggedInUser?.get(0)?.let { setLoggedInUser(it) }
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }


        })
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        LoginPreferences(application).saveLogedInUser(loggedInUser)
    }


     fun getLoggedInUser(): String? {
      return LoginPreferences(application).getLoggedInUser()?.MA_NamaLengkap
    }


    fun ResetPassword(email: String, callback: RemoteDataCallback) {

    dataSource.ResetPassword(email, object : RemoteDataCallback {
        override fun onSuccess(data: ApiResponse) {
            callback.onSuccess(data)
        }

        override fun onFailed(errorMessage: String?) {
            callback.onFailed(errorMessage)
        }


    })


}
    fun getUserProfile(MA_Nrp:Int, callback: RemoteDataCallback) {
        // handle login

        dataSource.getUserProfile(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                callback.onSuccess(data)
                data.loggedInUser?.get(0)?.let { setLoggedInUser(it) }
            }

            override fun onFailed(errorMessage: String?) {
                callback.onFailed(errorMessage)
            }


        })
    }

}
