package com.example.siapitk.data.localDataSource

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.example.siapitk.data.model.LoggedInUser

class LoginPreferences(val context: Context) {
    fun saveLogedInUser(loggedInUser: LoggedInUser) {
        Log.d("TAG",loggedInUser.toString())
        var userData = PreferenceManager.getDefaultSharedPreferences(context).edit();
        loggedInUser.MA_NamaLengkap?.let { userData.putString("MA_NamaLengkap", it) }
        loggedInUser.MA_NRP_Baru?.let { userData.putInt("MA_NRP_Baru", it) }
        loggedInUser.MA_email?.let { userData.putString("MA_email", it) }
        loggedInUser.MA_Nrp?.let { userData.putInt("MA_Nrp", it) }
        loggedInUser.MA_IMEI?.let { userData.putString("MA_IMEI", it) }
        userData.apply()
    }

    fun getLoggedInUser(): LoggedInUser? {

        var userData = PreferenceManager.getDefaultSharedPreferences(context).all
        var loggedInUser= LoggedInUser(
            MA_NamaLengkap = userData["MA_NamaLengkap"]?.toString(),
            MA_IMEI = userData["MA_IMEI"]?.toString(),
            MA_email = userData["MA_email"]?.toString(),
            MA_NRP_Baru = userData["MA_NRP_Baru"]?.toString()?.toInt(),
            MA_Nrp = userData["MA_Nrp"]?.toString()?.toInt()

        )
        return loggedInUser
    }

    fun clearUserPreferences(){
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply()
    }

}