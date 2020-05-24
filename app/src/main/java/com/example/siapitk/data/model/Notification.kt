package com.example.siapitk.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notification")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int,

    @SerializedName("date")
    @ColumnInfo(name = "date")
    var notificationDate: String?,

    @SerializedName("time")
    @ColumnInfo(name = "time")
    var notificationTime:String,

    @SerializedName("msg")
    @ColumnInfo(name = "msg")
    var notificationMsg: String?,

    @SerializedName("count")
    @ColumnInfo(name = "count")
    var notificationCount: String?

): Parcelable