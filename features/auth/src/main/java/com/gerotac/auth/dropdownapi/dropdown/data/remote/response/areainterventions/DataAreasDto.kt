package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Links
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Pagination
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea

data class DataAreasDto(
    val links: Links,
    val pagination: Pagination,
    val result: List<ResultArea>
)