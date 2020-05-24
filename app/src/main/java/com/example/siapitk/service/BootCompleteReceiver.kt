package com.example.siapitk.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(mContext: Context?, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val mIntent = Intent(mContext, AlarmService::class.java)
            if (mContext != null) {
                mContext.startService(mIntent)
            }
        }
    }
}
