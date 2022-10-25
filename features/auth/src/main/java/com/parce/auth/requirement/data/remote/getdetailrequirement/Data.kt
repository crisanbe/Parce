package com.parce.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import com.parce.auth.requirement.domain.model.detailrequirement.Areaintervention
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.domain.model.detailrequirement.File
import com.parce.auth.requirement.domain.model.detailrequirement.User
import java.io.Serializable

data class Data(
    @SerializedName("areaintervention") val areaintervention: Areaintervention,
    @SerializedName("cause_problem") val cause_problem: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("description") val description: String,
    @SerializedName("efect_problem") val efect_problem: String,
    @SerializedName("id") val id: Int,
    @SerializedName("impact_problem") val impact_problem: String,
    @SerializedName("relations") val relations: Relations,
    @SerializedName("user") val user: User
): Serializable

fun Data.toGetDetail(): DataResponse {
    return DataResponse(
        id = id,
        description = description,
        areaintervention = Areaintervention(name = areaintervention.name),
        cause_problem = cause_problem,
        created_at = created_at,
        efect_problem = efect_problem,
        impact_problem = impact_problem,
        user = User(name = user.name, role = user.role),
        relations = com.parce.auth.requirement.domain.model.detailrequirement.Relations(
            interventions = relations.interventions.mapIndexed { _, resultIntervention ->
                resultIntervention
            },
            users = relations.users.mapIndexed { _, resultUser ->
                resultUser
            },
            files = relations.files.mapIndexed { _, resultFiles ->
                File(
                    id = resultFiles.id,
                    url = resultFiles.url,
                    created_at = resultFiles.created_at
                )
            },
        )
    )
}