package com.gerotac.auth.requirement.domain.model.getrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetRequirement(
    val code: String? = null,
    val data: Data,
    val message: String,
    val state: Boolean,
    val status: String
) : Serializable

data class Data(
    val links: Links? = null,
    val pagination: Pagination? = null,
    val result: List<Result>? = null
) : Serializable

data class Result(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: Relations,
    val user: User
) : Serializable

data class User(
    val name: String,
    val role: String
) : Serializable

data class Relations(
    val files: List<File>,
    val interventions: List<Intervention>,
    val users: List<User>
) : Serializable

data class Intervention(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val type_intervention: String,
    val user: User
) : Serializable

data class File(
    val created_at: String,
    val filename: String,
    val id: Int,
    val url: String
) : Serializable


data class Links(
    val first: String? = null,
    val last: String,
    val next: String? = null,
    val prev: Any? = null
) : Serializable

data class Pagination(
    val current_page: Int? = null,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
) : Serializable

data class Areaintervention(
    val id: Int,
    val name: String
) : Serializable