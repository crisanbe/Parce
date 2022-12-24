package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.auth.requirement.domain.model.detailrequirement.DataResponse
import com.gerotac.auth.requirement.domain.model.detailrequirement.Relations

data class DataDto(
    val data: DataResponse,
    val message: String,
    val status: Boolean
)
fun DataDto.toGetDetail(): Data {
    return Data(
        data = DataResponse(
            areaintervention = data.areaintervention,
            cause_problem = data.cause_problem,
            created_at = data.created_at,
            description = data.description,
            id = data.id,
            efect_problem = data.efect_problem,
            impact_problem = data.impact_problem,
            relations = Relations(
                files = data.relations.files,
                interventions = data.relations.interventions
            ),
            user = data.user
        ),
        message = message,
        status = status
    )
}


fun RelationsDto.toGetFile(): Relations {
    return  Relations(
        files = files.map { file -> file.toContenidoFileModel()}
    )
}

fun File.toContenidoFileModel(): com.gerotac.auth.requirement.domain.model.detailrequirement.File {
    return com.gerotac.auth.requirement.domain.model.detailrequirement.File(
        created_at = created_at,
        id = id,
        url = url
    )
}
