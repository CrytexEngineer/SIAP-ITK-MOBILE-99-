package com.example.siapitk.data.model

import com.google.gson.annotations.SerializedName

data class PresenceCount(
    @SerializedName("MA_Nrp")
    var maNrp:Int?,
    @SerializedName("Jumlah_Pertemuan")
    var jumlahPertemuan:Int?,
    @SerializedName("Kehadiran")
    var kehadiran:Int?,
    @SerializedName("persentase")
    var persentase:Int?,
    @SerializedName("Sakit")
    var sakit:Int?,
    @SerializedName("Izin")
    var izin:Int?,
    @SerializedName("Alpha")
    var alpha:Int?,
    @SerializedName("Telat")
    var telat:Int?


)