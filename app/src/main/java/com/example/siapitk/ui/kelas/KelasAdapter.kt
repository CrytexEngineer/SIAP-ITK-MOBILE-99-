package com.example.siapitk.ui.kelas


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siapitk.R
import com.example.siapitk.data.model.Kelas
import com.example.siapitk.ui.presence.PresenceActivity
import kotlinx.android.extensions.LayoutContainer
import java.util.*

class KelasAdapter(val context: Context) :
    RecyclerView.Adapter<KelasAdapter.KelasAdapterViewHolder>() {

    class KelasAdapterViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        fun bindItem(kelas: Kelas, listener: (Kelas) -> Unit) {
            containerView.findViewById<TextView>(R.id.item_kelas_jam).text =
                kelas.jamMulai?.substring(0, 5) + "-" + kelas.jamUsai?.substring(0, 5)
            containerView.findViewById<TextView>(R.id.item_kelas_kelas).text = kelas.kelas
            containerView.findViewById<TextView>(R.id.item_kelas_matakuliah).text = kelas.mataKuliah
            containerView.findViewById<TextView>(R.id.item_kelas_ruang).text = kelas.jadwalRuangan
            containerView.findViewById<TextView>(R.id.item_kelas_dosen).text = kelas.namaPengajar
            containerView.findViewById<TextView>(R.id.item_kelas_persentase).text ="-"
            Log.d("ISMAYA", kelas.jumlahPertemuan.toString()+kelas.jumlahPertemuan.toString())
            if (kelas.jumlahPertemuan > 0) {
                containerView.findViewById<TextView>(R.id.item_kelas_persentase).text =
                    kelas.persentaseKehadiran + "%"
            }
            containerView.findViewById<FrameLayout>(R.id.item_matakuliah_container)
                .setOnClickListener { listener(kelas) }
        }

    }

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mListKelass: List<Kelas>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): KelasAdapterViewHolder {
        val itemView: View = mInflater.inflate(R.layout.item_mtakuliah_schedule, p0, false)
        return KelasAdapterViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return mListKelass?.size ?: 0
    }

    override fun onBindViewHolder(p0: KelasAdapterViewHolder, p1: Int) {
        val i = Intent(context, PresenceActivity::class.java)
        i.putExtra(context.getString(R.string.kelas_detail), mListKelass?.get(p1))
        mListKelass?.get(p1)?.let {
            p0.bindItem(it) {
                context.startActivity(i)
            }
        }
    }

    fun setListKelas(mListKelass: ArrayList<Kelas>) {
        this.mListKelass = mListKelass
        Log.d("ADAPTER", mListKelass.toString())
        notifyDataSetChanged()

    }
}

