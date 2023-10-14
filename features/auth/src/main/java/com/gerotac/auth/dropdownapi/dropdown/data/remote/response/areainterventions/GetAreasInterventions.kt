package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.GetAreasInterventions
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea

data class GetAreasInterventionsDto(
    val code: String,
    val data: DataAreasDto,
    val message: String,
    val state: Boolean,
    val status: String
)

data class DataAreasDto(
    val result: List<ResultArea>
)

fun GetAreasInterventionsDto.toResponseListAreas(): GetAreasInterventions {
    return GetAreasInterventions(
        code = code,
        data = Data(
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