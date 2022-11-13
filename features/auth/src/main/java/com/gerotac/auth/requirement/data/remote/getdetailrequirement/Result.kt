package com.gerotac.auth.requirement.data.remote.getdetailrequirement

data class Result(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: Relations,
    val user: User
)

fun Result.toGetDetail(): com.gerotac.auth.requirement.domain.model.detailrequirement.Result {
    return com.gerotac.auth.requirement.domain.model.detailrequirement.Result(
        id = id,
        description = description,
        areaintervention = com.gerotac.auth.requirement.domain.model.detailrequirement.Areaintervention(id = areaintervention.id, name = areaintervention.name),
        cause_problem = cause_problem,
        created_at = created_at,
        efect_problem = efect_problem,
        impact_problem = impact_problem,
        user = user.let { com.gerotac.auth.requirement.domain.model.detailrequirement.User(id = it.id, name = it.name, role = user.role) },
        relations = relations.toGetFile()
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