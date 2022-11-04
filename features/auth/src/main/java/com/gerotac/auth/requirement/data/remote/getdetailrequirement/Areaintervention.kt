package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Areaintervention(
    @SerializedName("name") val name: String? = null
): Serializable