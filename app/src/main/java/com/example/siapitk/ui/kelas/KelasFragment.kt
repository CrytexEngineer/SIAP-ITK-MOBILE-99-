package com.example.siapitk.ui.kelas


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siapitk.ApiUtils.KelasViewmodel
import com.example.siapitk.R
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.ui.login.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_kelas.*

class KelasFragment : Fragment() {

    private lateinit var adapter: KelasAdapter
    private lateinit var kelasViewModel: KelasViewmodel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutInflater = inflater.inflate(R.layout.fragment_kelas, container, false)
        return layoutInflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        activity?.application?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp }?.let {
            swlayout_kelas.setOnRefreshListener { showData(it)
              }

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.getWindow()?.getDecorView()
            ?.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        kelasViewModel = ViewModelProviders.of(this,
            activity?.application?.let { HomeViewModelFactory(it) }).get(KelasViewmodel::class.java)


        kelasViewModel.kelas.observe(this, Observer { t ->
            t?.let {
                it.kelasList?.let { it1 -> adapter.setListKelas(it1)
                    adapter.notifyDataSetChanged()
                    swlayout_kelas.setRefreshing(false);
                 }
            }
        })
        activity?.application?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp }?.let {

            showData(it)
        }

    }

    private fun initRecyclerView() {
        adapter = activity?.let { KelasAdapter(it) }!!
        rv_matakuliah.layoutManager = LinearLayoutManager(activity)
        rv_matakuliah.adapter = adapter
    }

    private fun showData(MA_Nrp: Int) {
        kelasViewModel.getKelas(MA_Nrp)

    }



}
