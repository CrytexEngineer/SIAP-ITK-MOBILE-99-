package com.example.siapitk.ui.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siapitk.R
import com.example.siapitk.data.model.Notification
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.activity_presence.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var adapter: NotificationAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NotificationViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        Log.d("BAR",supportActionBar.toString())
        supportActionBar?.title="Notifikasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        viewModel =
            ViewModelProviders.of(this, application?.let { NotificationViewModelFactory(it) })
                .get(NotificationViewModel::class.java)
        viewModel.getSavedNotification().observe(this, Observer { t ->
            swylayout_notification.isRefreshing = false
            t?.let {
                it.let { it ->
                    adapter.setListNotification(it as ArrayList<Notification>)

                }
            }
        })

        swylayout_notification.setOnRefreshListener {
            showData()
        }
        initRecyclerView()
        showData()


    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rv_notification)
        adapter = NotificationAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun showData() {

    }

}
