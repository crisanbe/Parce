package com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel

import java.io.Serializable

data class ResponseAcademic(
    val code: String,
    val `data`: ResponseData,
    val message: String,
    val state: Boolean,
    val status: String
) : Serializable

data class ResponseData(
    val result: List<Result>
) : Serializable

data class Result(
    val id: Int,
    val name: String
) : Serializable