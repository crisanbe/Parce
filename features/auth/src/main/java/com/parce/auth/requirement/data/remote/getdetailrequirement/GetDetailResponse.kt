package com.parce.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.parce.auth.requirement.domain.model.detailrequirement.GetDetailResponse
import com.parce.auth.requirement.domain.model.detailrequirement.Data
import com.parce.auth.requirement.domain.model.detailrequirement.Relations
import com.parce.auth.requirement.domain.model.detailrequirement.File
import com.parce.auth.requirement.domain.model.detailrequirement.User

data class GetDetailResponse(
    @SerializedName("data") val `data`: Data,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
) : Serializable

fun GetDetailResponse.toGetDetailRequirement(): GetDetailResponse {
    return GetDetailResponse(
        message = message,
        status = status,
        data = Data(
            id = data.id,
            description = data.description,
            areaintervention = data.areaintervention,
            cause_problem = data.cause_problem,
            created_at = data.created_at,
            efect_problem = data.efect_problem,
            impact_problem = data.impact_problem,
            user = User(
                name = data.user.name,
                role = data.user.role
            ),
            relations = Relations(
                interventions = data.relations.interventions.mapIndexed { _, resultIntervention ->
                    resultIntervention
                },
                users = data.relations.users.mapIndexed { _, resultUser ->
                    resultUser
                },
                files = data.relations.files.mapIndexed { _, resultFiles ->
                    File(
                        id = resultFiles.id,
                        url = resultFiles.url,
                        created_at = resultFiles.created_at
                    )
                },
            )
        )
    )
}