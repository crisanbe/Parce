package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RelationsDto(
    @SerializedName("files") val files: List<File>,
    @SerializedName("interventions") val interventions: List<Any>? = emptyList(),
    @SerializedName("users") val users: List<Any>? = emptyList()
): Serializable