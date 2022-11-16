package com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request

import com.google.gson.annotations.SerializedName

data class AssignRequest(
    @SerializedName("user") val userTeacher: List<Int>,
    @SerializedName("requierement") val idRequirement: Int
)
