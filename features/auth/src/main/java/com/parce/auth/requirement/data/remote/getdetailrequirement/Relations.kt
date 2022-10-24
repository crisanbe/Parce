package com.parce.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Relations(
    @SerializedName("files") val files: List<File>,
    @SerializedName("interventions") val interventions: List<Any>,
    @SerializedName("users") val users: List<Any>
): Serializable