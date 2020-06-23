package com.example.siapitk.ui.presence

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.siapitk.ApiUtils.PresenceViewModel
import com.example.siapitk.DaysUtils
import com.example.siapitk.R
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.data.model.Kelas
import kotlinx.android.synthetic.main.activity_presence.*

class PresenceActivity : AppCompatActivity() {

    private lateinit var adapter: PresenceAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PresenceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presence)
        initRecyclerView()

        var kelas = intent.getParcelableExtra<Kelas>(this.getString(R.string.kelas_detail))

        viewModel = ViewModelProviders.of(this, application?.let { PresenceViewModelFactory(it) })
            .get(PresenceViewModel::class.java)
        viewModel.presence.observe(this, Observer { t ->
            t?.let {
                it.presences?.let { it1 ->
                    adapter.setListPresence(it1)
                    adapter.notifyDataSetChanged()
                    swlayout_presence.isRefreshing = false
                }
            }
        })
        viewModel.presenceCount.observe(this, Observer { t ->
            t?.let {
                var count = it.presenceCount?.get(0)?.persentase

                presence_tv_count.text=it.presenceCount?.get(0)?.persentase.toString()+"%"
                presence_tv_pertemuan.text = it.presenceCount?.get(0)?.jumlahPertemuan.toString()
                presence_tv_sakit.text = it.presenceCount?.get(0)?.sakit.toString()
                presence_tv_izin.text = it.presenceCount?.get(0)?.izin.toString()
                presence_tv_alpha.text = it.presenceCount?.get(0)?.alpha.toString()


                if (it.presenceCount?.get(0)?.persentase.toString().equals("null")) {
                    presence_tv_count.text = "-"
                }

                if (it.presenceCount?.get(0)?.jumlahPertemuan.toString().equals("null")) {
                    presence_tv_pertemuan.text = "-"
                }

                if (it.presenceCount?.get(0)?.sakit.toString().equals("null")) {
                    presence_tv_sakit.text = "-"
                }
                if (it.presenceCount?.get(0)?.izin.toString().equals("null")) {
                    presence_tv_izin.text = "-"
                }
                if (it.presenceCount?.get(0)?.alpha.toString().equals("null")) {
                    presence_tv_alpha.text = "-"
                }



                if (kelas != null) {
                    presence_tv_matakuliah.text=kelas.mataKuliah
                    presence_tv_hari.text =
                        kelas.jadwalHari?.let { it1 -> DaysUtils().covertDays(it1) }
                    presence_tv_ruang.text = kelas.jadwalRuangan
                    presence_tv_jam.text =
                        kelas.jamMulai?.substring(0, 5) + "-" + kelas.jamUsai?.substring(0, 5)
                    presence_tv_kelas.text = kelas.kelas

                    if (kelas.jadwalRuangan.equals("NULL")) {
                        presence_tv_ruang.text = "-"
                    }


                }
            }
        })

        viewModel.errorMessege.observe(this, Observer { t ->
            t?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })


        application?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp }?.let {
            intent.getParcelableExtra<Kelas>(this.getString(R.string.kelas_detail)).matakuliahID
                ?.let { it1 ->
                    showData(
                        it, it1
                    )
                }
        }

        swlayout_presence.setOnRefreshListener {
            (LoginPreferences(this).getLoggedInUser()?.MA_Nrp?.let {
                intent.getParcelableExtra<Kelas>(this.getString(R.string.kelas_detail)).matakuliahID?.let { it1 ->
                    showData(
                        it, it1
                    )
                }
            })
        }
    }


    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.rv_presence)
        adapter = PresenceAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun showData(MA_Nrp: Int, MK_ID: String) {
        viewModel.getPresences(MA_Nrp, MK_ID)
        viewModel.getPresenceCount(MA_Nrp, MK_ID)
    }
}
