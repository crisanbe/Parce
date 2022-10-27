package com.parce.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import com.parce.auth.requirement.domain.model.detailrequirement.Areaintervention
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.domain.model.detailrequirement.File
import com.parce.auth.requirement.domain.model.detailrequirement.User
import java.io.Serializable

data class Data(
    @SerializedName("areaintervention") val areaintervention: Areaintervention? = null,
    @SerializedName("cause_problem") val cause_problem: String? = null,
    @SerializedName("created_at") val created_at: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("efect_problem") val efect_problem: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("impact_problem") val impact_problem: String,
    @SerializedName("relations") val relations: Relations? = null,
    @SerializedName("user") val user: User? = null
): Serializable

fun Data.toGetDetail(): DataResponse {
    return DataResponse(
        id = id,
        description = description,
        areaintervention = Areaintervention(name = areaintervention?.name),
        cause_problem = cause_problem,
        created_at = created_at,
        efect_problem = efect_problem,
        impact_problem = impact_problem,
        user = user?.let { User(name = it.name, role = user.role) },
        relations = com.parce.auth.requirement.domain.model.detailrequirement.Relations(
            interventions = relations?.interventions?.mapIndexed { _, resultIntervention ->
                resultIntervention
            },
            users = relations?.users?.mapIndexed { _, resultUser ->
                resultUser
            } ?: emptyList(),
            files = relations?.files?.mapIndexed { _, resultFiles ->
                File(
                    id = resultFiles.id,
                    url = resultFiles.url,
                    created_at = resultFiles.created_at
                )
            } ?: emptyList(),
        )
    )
}