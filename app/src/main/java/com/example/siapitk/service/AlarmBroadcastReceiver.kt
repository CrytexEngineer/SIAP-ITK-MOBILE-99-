package com.example.siapitk.service


import RetrofitInstance
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.siapitk.ApiUtils.NotificationDataService
import com.example.siapitk.data.RemoteDataCallback
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.data.model.ApiResponse
import com.example.siapitk.data.remoteDataSource.NotificationDataSource
import java.text.SimpleDateFormat
import java.util.*

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            Log.d("RECEIVE", "RECEIVE")


//            if (context != null) {
//                NotificationHelper.createNotification(context, "TES")
//                val mIntent = Intent(context, AlarmService::class.java)
//                context.startService(mIntent)
//            }
            // pengecekan dilakukan agar notifikasi tidak muncul berulang
//            if (getTimeNow() == intent.getStringExtra("validationTime")) {

            context?.let {

                LoginPreferences(
                    it
                ).getLoggedInUser()?.MA_Nrp?.let {
                    NotificationDataSource(
                        RetrofitInstance.getRetrofitInstance()
                            .create(NotificationDataService::class.java)
                    ).getNotification(
                        it,
                        object : RemoteDataCallback {
                            override fun onSuccess(data: ApiResponse) {
                                if (context != null) data.notification?.get(0)?.notificationMsg?.let {
                                    NotificationHelper.createNotification(
                                        context,
                                        LoginPreferences(context).getLoggedInUser()?.MA_NamaLengkap + ", " + it
                                    )

                                }
                            }

                            override fun onFailed(errorMessage: String?) {
                            Log.d("EROR", errorMessage.toString())
                            }
                        })
                }

                val mIntent = Intent(context, AlarmService::class.java)
                context.startService(mIntent)
            }

        }

//            if (intent.action == "android.intent.action.TIME_SET") {
//                context?.stopService(Intent(context, AlarmService::class.java))
//
//                // langkah ini dilakukan untuk memicu ulang agar service kembali menyala
//                // setelah melakukan uji coba mengganti tanggal service mati
//                Handler().postDelayed({ context?.startService(Intent(context, AlarmService::class.java)) }, 1000)
//            }
    }


    private fun getTimeNow(): String {
        val dateTimeMillis = System.currentTimeMillis()

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateTimeMillis

        return SimpleDateFormat("HH:mm:ss").format(calendar.time)
    }

}

