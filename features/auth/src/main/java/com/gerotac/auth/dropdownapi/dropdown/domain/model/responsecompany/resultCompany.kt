package com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany

import java.io.Serializable

data class ResponseCompany(
    val code: String,
    val `data`: DataCompany,
    val message: String,
    val state: Boolean,
    val status: String
) : Serializable

data class DataCompany(
    val result: List<ResultCompany>
) : Serializable

data class ResultCompany(
    val id: Int,
    val name: String
) : Serializable



