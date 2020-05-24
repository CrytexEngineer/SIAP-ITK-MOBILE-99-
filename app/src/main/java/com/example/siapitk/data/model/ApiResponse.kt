package com.example.siapitk.data.model


import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("message")
    var messege: String? = null

    @SerializedName("kelas")
    var kelasList: ArrayList<Kelas>? = null

    @SerializedName("properties")
    var properties: ArrayList<Properties>? = null

    @SerializedName("token")
    var token: ArrayList<Token>? = null

    @SerializedName("user")
    var loggedInUser: ArrayList<LoggedInUser>? = null

    @SerializedName("presenceCount")
    var presenceCount: ArrayList<PresenceCount>? = null

    @SerializedName("presences")
    var presences: ArrayList<Presence>? = null

    @SerializedName("notification")
    var notification: ArrayList<Notification>? = null

}