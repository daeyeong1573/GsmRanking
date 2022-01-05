package org.gsm.software.gsmranking.model.data

import android.net.Uri
import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("login")
    var id : String,
    @SerializedName("avatar_url")
    var imgUrl : String?,
    @SerializedName("bio")
    var bio : String
)
