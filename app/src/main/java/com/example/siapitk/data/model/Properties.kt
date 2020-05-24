package com.example.siapitk.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Properties(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("method")
    val method: String
) : Parcelable {
}