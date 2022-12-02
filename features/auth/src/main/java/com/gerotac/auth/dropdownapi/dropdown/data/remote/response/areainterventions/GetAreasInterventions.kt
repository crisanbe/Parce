package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.GetAreasInterventions
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Links
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Pagination
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea

data class GetAreasInterventionsDto(
    val code: String,
    val data: DataAreasDto,
    val message: String,
    val state: Boolean,
    val status: String
)

data class DataAreasDto(
    val links: Links,
    val pagination: Pagination,
    val result: List<ResultArea>
)

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
)

data class Pagination(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)

data class ResultAreaDto(
    val id: Int,
    val name: String
)


fun GetAreasInterventionsDto.toResponseListAreas(): GetAreasInterventions {
    return GetAreasInterventions(
        code = code,
        data = Data(
            links = Links(
                first = data.links.first,
                last = data.links.last,
                next = data.links.next,
                prev = data.links.prev
            ),
            pagination = Pagination(
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