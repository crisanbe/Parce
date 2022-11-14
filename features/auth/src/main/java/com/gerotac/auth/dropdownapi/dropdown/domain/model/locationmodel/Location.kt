package com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel

import java.io.Serializable

data class Location(
    val code: String,
    val `data`: Data,
    val message: String,
    val state: Boolean,
    val status: String
) : Serializable

data class Data(
    val result: List<ResultX>
) : Serializable

data class ResultX(
    val id: Int,
    val name: String,
    val relations: Relations
) : Serializable

data class Relations(
    val towns: List<Town>
) : Serializable

data class Town(
    val department: Department,
    val id: Int,
    val name: String
) : Serializable

data class Department(
    val id: Int,
    val name: String
) : Serializable

/*
data class Links(
    val first: String,
    val last: String,
    val next: Any,
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
) : Serializable*/
