package com.gerotac.auth.intervention.detailintervention.domain.model

import java.io.Serializable

data class DetailResponseIntervention(
    val data: Data,
    val message: String,
    val status: Boolean
):Serializable

data class Data(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: Relations,
    val requierement: Int,
    val status: String,
    val type_intervention: String,
    val user: User
):Serializable

data class Relations(
    val files: List<File>
):Serializable

data class File(
    val created_at: String,
    val filename: String,
    val id: Int,
    val url: String,
    var downloadedUri:String?=null,
    var isDownloading:Boolean = false,
):Serializable

data class User(
    val id: Int,
    val name: String,
    val role: String
):Serializable