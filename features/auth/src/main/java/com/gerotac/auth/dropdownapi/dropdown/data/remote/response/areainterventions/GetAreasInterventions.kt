package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.GetAreasInterventions
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea

data class GetAreasInterventionsDto(
    val code: String,
    val data: DataAreasDto,
    val message: String,
    val state: Boolean,
    val status: String
)

fun GetAreasInterventionsDto.toResponseListAreas(): GetAreasInterventions {
    return GetAreasInterventions(
        code = code,
        data = Data(
            links = com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Links(
                first = data.links.first,
                last = data.links.last,
                next = data.links.next,
                prev = data.links.prev
            ),
            pagination = com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Pagination(
                current_page = data.pagination.current_page,
                from = data.pagination.from,
                last_page = data.pagination.last_page,
                path = data.pagination.path,
                per_page = data.pagination.per_page,
                to = data.pagination.to,
                total = data.pagination.total
            ),
            result = data.result.mapIndexed { _, result ->
                ResultArea(
                    id = result.id,
                    name = result.name,
                )
            }

        ),
        message = message,
        state = state,
        status = status
    )
}