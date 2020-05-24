package com.example.siapitk.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Token(
    @SerializedName("PT_Token")
    val token: String?,
    @SerializedName("PT_BlockTime")
    val blockTime: String?,
    @SerializedName("MK_ID")
    val matakuliahID: String?,
    @SerializedName("MK_Mata_Kuliah")
    val namaMatakuliah: String?,
    @SerializedName("KE_Kelas")
    val namaKelas: String?,
    @SerializedName("PE_NamaLengkap")
    val namaPengajar: String?
) : Parcelable