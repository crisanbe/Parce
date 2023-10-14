package com.gerotac.auth.requirement.domain.model.getrequirement

import java.io.Serializable

data class GetRequirement(
    val code: String,
    val data: Data,
    val message: String,
    val state: Boolean,
    val status: String
) : Serializable

data class Data(
    val links: Links,
    val pagination: Pagination,
    val result: List<Result>
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
    val interventions: List<Any>,
    val users: List<Any>
) : Serializable

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
) : Serializable

data class Pagination(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
) : Serializable

data class Areaintervention(
    val name: String
) : Serializable