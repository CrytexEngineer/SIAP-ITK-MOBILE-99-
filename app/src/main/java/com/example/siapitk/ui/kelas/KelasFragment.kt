package com.example.siapitk.ui.kelas


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siapitk.ApiUtils.KelasViewmodel
import com.example.siapitk.MainActivity
import com.example.siapitk.R
import com.example.siapitk.data.localDataSource.LoginPreferences
import kotlinx.android.synthetic.main.fragment_kelas.*

class KelasFragment : Fragment() {

    private lateinit var adapter: KelasAdapter
    private lateinit var kelasViewModel: KelasViewmodel

    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_kelas, container, false)
        }

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        activity?.application?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp }?.let {
            swlayout_kelas.setOnRefreshListener {
                showData(it)
            }

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setRetainInstance(true)
        kelasViewModel = (activity as MainActivity).kelasViewModel
        activity?.getWindow()?.getDecorView()
            ?.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        kelasViewModel.kelas.observe(this, Observer { t ->
            t?.let {
                it.kelasList?.let { it1 ->
                    adapter.setListKelas(it1)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController()

        Log.d("NAVIGATE", item.itemId.toString() + " = " + R.id.nav_home)

        if (item.itemId == R.id.nav_home) {
//            if(navController.popBackStack(R.id.nav_home,false)){
//                Log.d("DESTINATION","exits")
//                navController.popBackStack()
//            }else{
            navController.navigate(R.id.action_nav_kelas_to_nav_home)
//            }
        }

        return super.onOptionsItemSelected(item)

    }


}
