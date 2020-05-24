package com.example.siapitk.ui.login


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siapitk.R
import com.example.siapitk.data.LoginRepository
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.model.LoggedInUser

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    val _resetPasswordNotification = MutableLiveData<ApiResponse>()

    val userProfile = MutableLiveData<LoggedInUser>()

    fun  haslogin(){

        _loginResult.value= LoginResult(success = repository.getLoggedInUser()?.let { LoggedInUserView(displayName = it) })
    }


    fun login(username: String, password: String, imei: String) {
        // can be launched in a separate asynchronous job

        repository.login(username, password, imei, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                var loggedInUser = data.loggedInUser?.get(0)

                if (loggedInUser != null) {
                    _loginResult.value =
                        LoginResult(success = loggedInUser?.MA_NamaLengkap?.let {
                            LoggedInUserView(
                                displayName = it
                            )
                        })
                } else {
                    _loginResult.value = LoginResult(error = data?.properties?.get(0)?.msg)
                }
            }

            override fun onFailed(errorMessage: String?) {
                _loginResult.value = LoginResult(error = errorMessage)
            }

        })


    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun ResetPassword(email: String) {

        repository.ResetPassword(email, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
                _resetPasswordNotification.value = data
            }

            override fun onFailed(errorMessage: String?) {

                Log.d("EROR",errorMessage)
            }
        })
    }

    fun getUserProfile(MA_Nrp: Int) {
        repository.getUserProfile(MA_Nrp, object : RemoteDataCallback {
            override fun onSuccess(data: ApiResponse) {
            userProfile.value= data.loggedInUser?.get(0)
            }

            override fun onFailed(errorMessage: String?) {

            }
        })
    }
}
