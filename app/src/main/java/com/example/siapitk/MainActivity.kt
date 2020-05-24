package com.example.siapitk


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.service.AlarmService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStartJobIntentService()
        setSupportActionBar(bottom_appbar)



        btn_action_scan.setOnClickListener {
            startActivityForResult(
                Intent(this, QrScannerActivity::class.java),
                QrScannerActivity.LAUNCH_SCANNER

            )
        }


    }


    override fun onResume() {
        super.onResume()
        if (LoginPreferences(this).getLoggedInUser()?.MA_Nrp == null) {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun onStartJobIntentService() {
        val mIntent = Intent(this, AlarmService::class.java)
        mIntent.putExtra("MK_ID", LoginPreferences(this).getLoggedInUser()?.MA_Nrp)
        startService(mIntent)
    }


}