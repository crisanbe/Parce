package com.gerotac.auth.intervention.createintervention.data.remote.request

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

data class SaveInterventionRequest(
    val type_intervention: RequestBody,
    val description: RequestBody,
    val requierement_id: Int,
    val file: MutableList<MultipartBody.Part> = mutableListOf()
)