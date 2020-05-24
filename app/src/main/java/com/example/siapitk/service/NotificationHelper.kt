package com.example.siapitk.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.siapitk.R


class NotificationHelper() {


    companion object {
        val NOTIFICATION_ID = 1
        var CHANNEL_ID = "channel_01"
        var CHANNEL_NAME: CharSequence = "SIAP_ITK"


        fun createNotification(context: Context, messege: String) {

            Log.d("CEK", "CEK")

            val mNotificationManager =

                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
//      .setLargeIcon(BitmapFactory.decodeResource(resources, R.))
                .setContentTitle("Absen Presensi")
                .setContentText(messege)
//            .setSubText(resources.getString(R.string.subtext))
                .setAutoCancel(true)

            /*
            Untuk android Oreo ke atas perlu menambahkan notification channel
            */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = CHANNEL_NAME as String?
                channel.enableVibration(true);
                mBuilder.setChannelId(CHANNEL_ID)
                mNotificationManager.createNotificationChannel(channel)
            }

            val notification = mBuilder.build()
            mNotificationManager.notify(NOTIFICATION_ID, notification)

        }
    }
}

