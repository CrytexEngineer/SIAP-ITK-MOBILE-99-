package com.example.siapitk

import android.R.id.home
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.siapitk.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {
    val waktu_loading=1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Handler().postDelayed(Runnable { //setelah loading maka akan langsung berpindah ke home activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, waktu_loading)

    }
}
