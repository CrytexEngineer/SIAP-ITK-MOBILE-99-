package com.example.siapitk.ui.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.siapitk.ApiUtils.KelasViewmodel
import com.example.siapitk.ApiUtils.PresenceViewModel
import com.example.siapitk.MainActivity
import com.example.siapitk.ProfileActivity
import com.example.siapitk.R
import com.example.siapitk.ViewModel.NotificationViewModelFactory
import com.example.siapitk.ViewModel.PresenceViewModelFactory
import com.example.siapitk.data.localDataSource.LoginPreferences
import com.example.siapitk.data.model.PresenceCount
import com.example.siapitk.ui.kelas.KelasAdapter
import com.example.siapitk.ui.login.HomeViewModelFactory
import com.example.siapitk.ui.notification.NotificationActivity
import com.example.siapitk.ui.notification.NotificationViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_notification.*
import kotlinx.android.synthetic.main.item_home_presence_highlight.*


class HomeFragment : Fragment() {
    private lateinit var adapter: KelasAdapter
    private lateinit var kelasViewModel: KelasViewmodel
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var presenceViewModel: PresenceViewModel

    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false)
        }

        return mView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        tv_home_user_picture?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

        tv_home_user_picture.text = context?.let {
            LoginPreferences(it).getLoggedInUser()?.MA_NamaLengkap?.get(0).toString()
        }
        tv_home_user_name.text =
            context?.let { LoginPreferences(it).getLoggedInUser()?.MA_NamaLengkap }
        tv_home_user_email.text = context?.let { LoginPreferences(it).getLoggedInUser()?.MA_email }

        btn_home_action_notification.setOnClickListener {

            startActivity(Intent(activity, NotificationActivity::class.java))
        }

        swlayout_home.setOnRefreshListener {
                context?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp?.let { showData(it) } }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setRetainInstance(true)
        activity?.getWindow()?.getDecorView()
            ?.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        kelasViewModel = (activity as MainActivity).kelasViewModel
        presenceViewModel = (activity as MainActivity).presenceViewModel
        notificationViewModel = (activity as MainActivity).notificationViewModel

        kelasViewModel = ViewModelProviders.of(this,
            activity?.application?.let { HomeViewModelFactory(it) }).get(KelasViewmodel::class.java)

        presenceViewModel.presenceCount.observe(this, Observer { t ->
            swlayout_home.isRefreshing=false
            t?.let {
                var count = t.presenceCount?.get(0)
                if (count?.kehadiran.toString().equals("null")) {
                    tv_home_presence_count.text = "0 Kehadiran"
                } else {
                    tv_home_presence_count.text =
                        t.presenceCount?.get(0)?.kehadiran.toString() + " Kehadiran"
                }

                count?.let { it1 ->
                    initChartStatistic(it1)
                    count?.let { it1 -> initChartPresenceAccuracy(it1) }
                }
            }

        }
        )

//    kelasViewModel.kelas.observe(this, Observer { t ->
//        t?.let {
//            it.kelasList?.let { it1 -> adapter.setListKelas(it1) }
//        }
//    })


        notificationViewModel.remoteNotification.observe(this, Observer { t ->
            swlayout_home.isRefreshing=false
            t?.let {
                tv_home_highlight_content.text = it.notificationMsg
                if (!it.notificationCount.equals("0")) {
                    img_home_badge_presence_count.background =
                        context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1,
                                R.drawable.badge_polygon_warning_big
                            )
                        }
                    img_home_badge_presence_count.text = it.notificationCount
                } else {
                    img_home_badge_presence_count.background =
                        context?.let { it1 ->
                            ContextCompat.getDrawable(
                                it1,
                                R.drawable.badge_polygon_check_big
                            )
                        }
                    img_home_badge_presence_count.text = "   "
                }

            }
        })

        activity?.application?.let { LoginPreferences(it).getLoggedInUser()?.MA_Nrp }?.let {
            Log.d(
                "TAG",
                LoginPreferences(requireActivity().application).getLoggedInUser().toString()
            )
            showData(
                it
            )
        }

    }

    private fun initRecyclerView() {
//        adapter = activity?.let { KelasAdapter(it) }!!
//        rv_matakuliah.layoutManager = LinearLayoutManager(activity)
//        rv_matakuliah.adapter = adapter
    }

    private fun initChartStatistic(count: PresenceCount) {

        val listPie = ArrayList<PieEntry>()
        val listColors = ArrayList<Int>()
        var sakit = count?.sakit?.toFloat()?.div(count.kehadiran!!)?.times(100F)
        var izin = count?.izin?.toFloat()?.div(count.kehadiran!!)?.times(100F)
        var alpha = count?.alpha?.toFloat()?.div(count.kehadiran!!)?.times(100F)
        var hadir = count.kehadiran?.minus(count?.izin!!)?.minus(count.sakit!!)?.toFloat()
            ?.div(count?.kehadiran!!)?.times(100F)

        tv_legend_sakit.text = count?.sakit.toString() + " Sakit"
        tv_legend_izin.text = count?.izin.toString() + " Izin"
        tv_legend_alpha.text = count?.alpha.toString() + " Alpha"
        tv_legend_hadir.text =
            count?.kehadiran!!.minus(count?.sakit!!).minus(count?.izin!!).minus(count?.alpha!!)
                .toString() + " Hadir"

        listPie.add(
            PieEntry(
                hadir!!,
                "Hadir: " + (count.kehadiran?.minus(count.izin!!)?.minus(count.sakit!!))
            )
        )

        listPie.add(PieEntry(sakit!!, "Sakit: " + count.sakit))
        listPie.add(PieEntry(izin!!, "Izin: " + count.izin))
        listPie.add(PieEntry(alpha!!, "Alpha: " + count.alpha))

        listColors.add(resources.getColor(R.color.colorPieStatGreen))
        listColors.add(resources.getColor(R.color.colorPieStatBlue))
        listColors.add(resources.getColor(R.color.colorPieStatYellow))
        listColors.add(resources.getColor(R.color.colorPieStatRed))

        piechart.setUsePercentValues(true)
        piechart.legend.isEnabled = false
        piechart.setHoleRadius(70f);
        piechart.setDrawHoleEnabled(true);
        piechart2.setDrawSliceText(false)
        piechart.setTransparentCircleRadius(1f);
        piechart.description.isEnabled = false
        piechart.setDrawSliceText(false)
        piechart.animateY(3000, Easing.EaseInOutCubic)

        val pieDataSet = PieDataSet(listPie, "")
        pieDataSet.colors = listColors
        pieDataSet.sliceSpace = 3F
        pieDataSet.selectionShift = 5F
        val pieData = PieData(pieDataSet)
        piechart.data = pieData


    }


    fun initChartPresenceAccuracy(count: PresenceCount) {


        piechart2.setUsePercentValues(true)
        piechart2.legend.isEnabled = false
        piechart2.isDrawHoleEnabled = false
        piechart2.description.isEnabled = false
        piechart2.setHoleColor(R.color.colorPieAccuracyBackround)
        piechart2.animateY(1400, Easing.EaseInOutQuad)
        piechart2.setDrawHoleEnabled(true);
        piechart2.setHoleRadius(70f);
        piechart2.setTransparentCircleRadius(10f);
        piechart2.setDrawSliceText(false)
        piechart2.description.isEnabled = false

        val listPie = ArrayList<PieEntry>()
        val listColors = ArrayList<Int>()
        var telat = count?.telat?.div(count.kehadiran?.toFloat()!!)?.times(100F)
        var tepatWaktu =
            count?.kehadiran!!.minus(count?.telat!!).toFloat().div(count?.kehadiran!!).times(100f)


        tv_home_accuracy_late.text = count?.telat?.toString()
        tv_home_accuracy_not_late.text = count?.kehadiran!!.minus(count.telat!!)?.toString()


        listColors.add(resources.getColor(R.color.colorPieAccuracyWhite))
        listPie.add(PieEntry(tepatWaktu!!, "Tepat Waktu: " + count.kehadiran?.minus(count.telat!!)))
        listColors.add(resources.getColor(R.color.colorPieAccuracyBlue))
        listPie.add(PieEntry(telat!!, "Telat: " + count.telat))

        val pieDataSet = PieDataSet(listPie, "")
        pieDataSet.colors = listColors
        val pieData = PieData(pieDataSet)
        piechart2.data = pieData
    }

    private fun showData(MA_Nrp: Int) {
        kelasViewModel.getKelas(MA_Nrp)
//        notificationViewModel.getRemoteNotification(MA_Nrp)
        presenceViewModel.getPresenceCount(MA_Nrp, "all")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val navController = findNavController()


        Log.d("NAVIGATE", item.itemId.toString() + " = " + R.id.nav_home)

        if (item.itemId == R.id.nav_kelas) {
//            if(navController.popBackStack(R.id.nav_kelas,false)){
//                Log.d("DESTINATION","exits")
//            }else{
                navController.navigate(R.id.action_nav_home_to_nav_kelas)
//            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onPause() {
        super.onPause()

    }
}
