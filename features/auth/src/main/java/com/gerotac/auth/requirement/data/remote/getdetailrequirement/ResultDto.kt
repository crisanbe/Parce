package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.gerotac.auth.requirement.domain.model.detailrequirement.Result

data class ResultDto(
    val areaintervention: Areaintervention? = null,
    val cause_problem: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val efect_problem: String? = null,
    val id: Int? = null,
    val impact_problem: String? = null,
    val relations: Relations? = null,
    val user: User? = null
)

fun ResultDto.toGetDetail(): Result {
    return Result(
        id = id,
        description = description,
        areaintervention = areaintervention,
        cause_problem = cause_problem,
        created_at = created_at,
        efect_problem = efect_problem,
        impact_problem = impact_problem,
        user = user,
        relations = relations?.toGetFile()
    )
}

fun Relations.toGetFile(): com.gerotac.auth.requirement.domain.model.detailrequirement.Relations {
    return  com.gerotac.auth.requirement.domain.model.detailrequirement.Relations(files = files.map { file -> file.toContenidoFileModel()})
}

fun File.toContenidoFileModel(): com.gerotac.auth.requirement.domain.model.detailrequirement.File {
    return com.gerotac.auth.requirement.domain.model.detailrequirement.File(
        created_at = created_at,
        id = id,
        url = url
    )
}