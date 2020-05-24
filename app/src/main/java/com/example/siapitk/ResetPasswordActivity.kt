package com.example.siapitk

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.siapitk.ui.login.LoginViewModel
import com.example.siapitk.ui.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_reset_password.*


class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        val MA_Email = edt_reset_password_email

        btn_backward_reset_password.setOnClickListener {
            finish()

        }

        val viewModel = ViewModelProviders.of(this, LoginViewModelFactory(application))
            .get(LoginViewModel::class.java)
        viewModel._resetPasswordNotification
            .observe(this, Observer {
                progress_reset_password.visibility = View.INVISIBLE
                it.messege?.let { it1 -> showDialog(it1) }
//                Toast.makeText(this, it.messege, Toast.LENGTH_LONG).show()
                if (it.loggedInUser != null) {
                    startCountDown()
                } else {
                    btn_reset_password.isEnabled = true
                }
            })

        btn_reset_password.setOnClickListener {
            progress_reset_password.visibility = View.VISIBLE
            btn_reset_password.isEnabled = false
            viewModel.ResetPassword(MA_Email.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun startCountDown() {
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_reset_password_ccuntdown.text =
                    "Kirim ulang dalam: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                btn_reset_password.isEnabled = true
                tv_reset_password_ccuntdown.visibility = View.INVISIBLE
            }
        }.start()

    }

    fun showDialog(messege: String) {
        AlertDialog.Builder(this)
            .setTitle("Informasi")
            .setMessage(messege).setPositiveButton("OK", DialogInterface.OnClickListener(
                { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            )).show()
    }


}
