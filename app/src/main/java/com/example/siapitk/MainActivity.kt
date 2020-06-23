package com.example.siapitk


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.siapitk.ApiUtils.KelasViewmodel
import com.example.siapitk.ApiUtils.PresenceViewModel
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.service.AlarmService
import com.example.siapitk.ui.scanner.QrScannerActivity
import com.example.siapitk.ui.login.HomeViewModelFactory
import com.example.siapitk.ui.notification.NotificationViewModel
import com.example.siapitk.ui.notification.NotificationViewModelFactory
import com.example.siapitk.ui.presence.PresenceViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kelas.*


class MainActivity : AppCompatActivity() {

    lateinit var kelasViewModel: KelasViewmodel
    lateinit var notificationViewModel: NotificationViewModel
    lateinit var presenceViewModel: PresenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStartJobIntentService()
        setSupportActionBar(bottom_appbar)

        kelasViewModel = ViewModelProviders.of(this,
            application?.let { HomeViewModelFactory(it) }).get(KelasViewmodel::class.java)

        notificationViewModel = ViewModelProviders.of(this,
            application?.let { NotificationViewModelFactory(it) })
            .get(NotificationViewModel::class.java)

        presenceViewModel = ViewModelProviders.of(this,
            application?.let { PresenceViewModelFactory(it) })
            .get(PresenceViewModel::class.java)


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
