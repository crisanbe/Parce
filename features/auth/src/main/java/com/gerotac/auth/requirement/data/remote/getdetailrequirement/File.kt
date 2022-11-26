package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import java.io.Serializable

data class File(
    val created_at: String,
    val filename: String,
    val id: Int,
    val url: String
) : Serializable