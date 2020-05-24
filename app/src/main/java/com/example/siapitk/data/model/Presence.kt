package com.example.siapitk.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Presence(
    @SerializedName("PR_ID")
    var prId: Int?,
    @SerializedName("created_at")
    var createrAt: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("PR_PT_ID")
    var prPtId: Int?,
    @SerializedName("PR_KE_ID")
    var prKeId: Int?,
    @SerializedName("PR_KU_ID")
    var prKuId: Int?,
    @SerializedName("PR_KU_MA_Nrp")
    var prKuMaNrp: Int?,
    @SerializedName("PR_Type")
    var prType: String?,
    @SerializedName("PR_IsLAte")
    var prIsLate: String?,
    @SerializedName("PR_Keterangan")
    var prKeterangan: String?,
    @SerializedName("PT_Name")
    var ptName: String?

):Parcelable