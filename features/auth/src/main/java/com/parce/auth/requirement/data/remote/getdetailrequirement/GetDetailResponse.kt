package com.parce.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import com.parce.auth.requirement.domain.model.detailrequirement.*
import com.parce.auth.requirement.domain.model.detailrequirement.Relations
import com.parce.auth.requirement.domain.model.detailrequirement.User
import java.io.Serializable

data class GetDetailResponse(
    @SerializedName("data") val data: Data,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
) : Serializable

fun GetDetailResponse.toGetDetailRequirement(): DetailResponse {
    return DetailResponse(
        message = message,
        status = status,
        data = DataResponse(
            id = data.id,
            description = data.description,
            areaintervention = data.areaintervention,
            cause_problem = data.cause_problem,
            created_at = data.created_at,
            efect_problem = data.efect_problem,
            impact_problem = data.impact_problem,
            user = data.user?.let {
                User(
                    name = it.name,
                    role = data.user.role
                )
            },
            relations = Relations(
                interventions = data.relations?.interventions?.mapIndexed { _, resultIntervention ->
                    resultIntervention
                },
                users = data.relations?.users?.mapIndexed { _, resultUser ->
                    resultUser
                } ?: emptyList(),
                files = data.relations?.files?.mapIndexed { _, resultFiles ->
                    FileResponse(
                        id = resultFiles.id,
                        url = resultFiles.url,
                        created_at = resultFiles.created_at
                    )
                } ?: emptyList(),
            )
        )
    )
}
