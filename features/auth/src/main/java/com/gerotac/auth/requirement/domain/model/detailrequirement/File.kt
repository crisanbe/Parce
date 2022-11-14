package com.gerotac.auth.requirement.domain.model.detailrequirement

data class File(
    val created_at: String,
    val id: Int,
    val url: String,
    var downloadedUri:String?=null,
    var isDownloading:Boolean = false,
)