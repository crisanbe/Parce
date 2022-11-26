package com.gerotac.auth.requirement.domain.model.detailrequirement

data class File(
    val created_at: String,
    val filename: String? = null,
    val id: Int,
    val url: String,
    var downloadedUri:String?=null,
    var isDownloading:Boolean = false,
)