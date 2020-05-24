
package com.example.siapitk.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kelas(
    @SerializedName("KU_MA_Nrp")
    val nrp: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("KE_KR_MK_ID")
    val matakuliahID: String?,
    @SerializedName("KE_Kelas")
    val kelas: String?,
    @SerializedName("KE_PE_NIPPengajar")
    val nipPengajar: String?,
    @SerializedName("KE_Jadwal_IDHari")
    val jadwalHari: Int?,
    @SerializedName("KE_Jadwal_JamMulai")
    val jamMulai: String?,
    @SerializedName("KE_Jadwal_JamUsai")
    val jamUsai: String?,
    @SerializedName("KE_Jadwal_Ruangan")
    val jadwalRuangan: String?,
    @SerializedName("PE_NamaLengkap")
    val namaPengajar: String?,
    @SerializedName("MK_Mata_Kuliah")
    val mataKuliah: String?,
    @SerializedName("KE_Count")
    val persentaseKehadiran: String?,
    @SerializedName("Jumlah_Pertemuan")
    var jumlahPertemuan: Int
) : Parcelable


