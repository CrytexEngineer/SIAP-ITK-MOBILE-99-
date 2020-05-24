package com.example.siapitk.ui.notification

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.siapitk.R
import com.example.siapitk.data.model.Notification
import kotlinx.android.extensions.LayoutContainer

class NotificationAdapter(val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.NotificationAdapterViewHolder>() {


    class NotificationAdapterViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {


        fun bindItem(context: Context, notification: Notification) {


            containerView.findViewById<TextView>(R.id.item_tv_notification_content).text =
                notification.notificationMsg
            containerView.findViewById<TextView>(R.id.item_tv_notification_date).text =
                notification.notificationDate
            containerView.findViewById<TextView>(R.id.item_tv_notification_time).text =
                notification.notificationTime.substring(0, 5)

            if (!notification.notificationCount.equals("0")) {
                containerView.findViewById<TextView>(R.id.item_notification_img_badge).background =
                    context?.let { it1 ->
                        ContextCompat.getDrawable(
                            it1,
                            R.drawable.badge_polygon_warning
                        )
                    }
                containerView.findViewById<TextView>(R.id.item_notification_img_badge).text =
                    notification.notificationCount
            } else {
                containerView.findViewById<TextView>(R.id.item_notification_img_badge).background =
                    context?.let { it1 ->
                        ContextCompat.getDrawable(
                            it1,
                            R.drawable.badge_polygon_check
                        )
                    }
            }
        }

    }

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mListNotification: ArrayList<Notification>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NotificationAdapterViewHolder {
        val itemView: View = mInflater.inflate(R.layout.item_notification, p0, false)
        return NotificationAdapterViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return mListNotification?.size ?: 0
    }

    override fun onBindViewHolder(p0: NotificationAdapter.NotificationAdapterViewHolder, p1: Int) {
        mListNotification?.get(p1)?.let { p0.bindItem(context, it) }
    }

    fun setListNotification(mListNotification: ArrayList<Notification>) {
        this.mListNotification = mListNotification
        notifyDataSetChanged()

    }
}

