package com.example.siapitk.ui.presence

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siapitk.R
import com.example.siapitk.data.model.Presence
import kotlinx.android.extensions.LayoutContainer
import java.util.*


class PresenceAdapter(var context: Context) :
    RecyclerView.Adapter<PresenceAdapter.PresenceAdapterViewHolder>() {

    class PresenceAdapterViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        fun bindItem(presence: Presence, listener: (Presence) -> Unit) {

            var dateTime = presence.createrAt
            var date: String = dateTime?.substring(0, 9).toString()
            var time: String = dateTime?.substring(11).toString()

            containerView.findViewById<TextView>(R.id.item_tv_presence_title).text = presence.ptName
            containerView.findViewById<TextView>(R.id.item_tv_presence_time).text =
                time.substring(0, 5)
            containerView.findViewById<TextView>(R.id.item_tv_presence_is_late).text =
                presence.prIsLate
            containerView.findViewById<TextView>(R.id.item_tv__presence_note).text =
                presence.prKeterangan
            containerView.findViewById<TextView>(R.id.item_tv_presence_date).text = date

            if (presence.prKeterangan.equals("IZIN")) {
                containerView.findViewById<ImageView>(R.id.item_presence_img_badge)
                    .setImageResource(R.drawable.badge_polygon_izin)
            }
            if (presence.prKeterangan.equals("SAKIT")) {
                containerView.findViewById<ImageView>(R.id.item_presence_img_badge)
                    .setImageResource(R.drawable.badge_polygon_sakit)
            }
            if (presence.prKeterangan.equals("ALPHA")) {
                containerView.findViewById<ImageView>(R.id.item_presence_img_badge)
                    .setImageResource(R.drawable.badge_polygon_alpha)
            }
        }

    }

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mListPresence: List<Presence>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PresenceAdapterViewHolder {
        val itemView: View = mInflater.inflate(R.layout.item_presence, p0, false)
        return PresenceAdapterViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return mListPresence?.size ?: 0
    }

    override fun onBindViewHolder(p0: PresenceAdapterViewHolder, p1: Int) {
//        val i = Intent(activity, DetailActivity::class.java)
//        i.putExtra(context.getString(R.string.Kelas_DETAIL), mListKelass?.get(p1))
        mListPresence?.get(p1)?.let {
            p0.bindItem(it) {
//                  Kelas: Kelas ->
//                activity.startActivity(i)
            }
//
//
        }
    }

    fun setListPresence(mListPresence: ArrayList<Presence>) {
        this.mListPresence = mListPresence
        Log.d("ADAPTER", mListPresence.toString())
        notifyDataSetChanged()

    }
}

