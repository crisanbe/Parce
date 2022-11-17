package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions

data class Pagination(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)