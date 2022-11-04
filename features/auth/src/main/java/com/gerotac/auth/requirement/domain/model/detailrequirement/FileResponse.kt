package com.gerotac.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class FileResponse(
    val created_at: String,
    val id: Int,
    val url: String,
    var downloadedUri:String? = null,
    var isDownloading:Boolean = false
): Serializable