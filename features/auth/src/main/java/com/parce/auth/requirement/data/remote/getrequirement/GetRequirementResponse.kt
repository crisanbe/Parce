package com.parce.auth.requirement.data.remote.getrequirement

import com.google.gson.annotations.SerializedName
import com.parce.auth.requirement.domain.model.getrequirement.*
import com.parce.auth.requirement.domain.model.getrequirement.Data
import com.parce.auth.requirement.domain.model.getrequirement.Links
import com.parce.auth.requirement.domain.model.getrequirement.Pagination
import com.parce.auth.requirement.domain.model.getrequirement.Relations
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.domain.model.getrequirement.User
import java.io.Serializable

data class GetRequirementResponse(
    @SerializedName("code") val code: String,
    @SerializedName("data") val `data`: Data,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
) : Serializable

data class Data(
    @SerializedName("links") val links: Links,
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("result") val result: List<Result>
) : Serializable

data class Result(
    @SerializedName("areaintervention") val areaintervention: Areaintervention,
    @SerializedName("cause_problem") val cause_problem: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("description") val description: String,
    @SerializedName("efect_problem") val efect_problem: String,
    @SerializedName("id") val id: Int,
    @SerializedName("impact_problem") val impact_problem: String,
    @SerializedName("relations") val relations: Relations,
    @SerializedName("user") val user: User
) : Serializable

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
) : Serializable

data class Relations(
    @SerializedName("interventions") val interventions: List<Any>,
    @SerializedName("users") val users: List<Any>
) : Serializable

data class Links(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: Any
) : Serializable

data class Pagination(
    @SerializedName("current_page") val current_page: Int? = null,
    @SerializedName("from") val from: Int,
    @SerializedName("last_page") val last_page: Int,
    @SerializedName("path") val path: String,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("to") val to: Int,
    @SerializedName("total") val total: Int
) : Serializable

data class Areaintervention(
    @SerializedName("name") val name: String
) : Serializable

fun GetRequirementResponse.toGetRequirements(): List<Result> {
    val result = data.result.mapIndexed { _, result ->
        Result(
            areaintervention = result.areaintervention,
            cause_problem = result.cause_problem,
            created_at = result.created_at,
            description = result.description,
            efect_problem = result.efect_problem,
            id = result.id,
            impact_problem = result.impact_problem,
            user = User(
                name = result.user.name,
                role = result.user.role
            ),
            relations = Relations(
                interventions = result.relations.interventions.mapIndexed { _, intevention ->
                },
                users = result.relations.users.mapIndexed { _, user ->
                },
            )
        )
    }
    return result
}

fun GetRequirementResponse.toGetPagination(): Pagination {
    return Pagination(
        current_page = data.pagination.current_page,
        from = data.pagination.from,
        last_page = data.pagination.last_page,
        path = data.pagination.path,
        per_page = data.pagination.per_page,
        to = data.pagination.to,
        total = data.pagination.total
    )
}

fun GetRequirementResponse.toGetRequirement(): GetRequirement {
    return GetRequirement(
        code = code,
        data = Data(
            links = Links(
                first = data.links?.first,
                last = data.links?.last ?: "",
                next = data.links?.next,
                prev = data.links?.prev
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
                Result(
                    areaintervention = result.areaintervention,
                    cause_problem = result.cause_problem,
                    created_at = result.created_at,
                    description = result.description,
                    efect_problem = result.efect_problem,
                    id = result.id,
                    impact_problem = result.impact_problem,
                    user = User(
                        name = result.user.name,
                        role = result.user.role
                    ),
                    relations = Relations(
                        interventions = result.relations.interventions.mapIndexed { _, intevention ->
                        },
                        users = result.relations.users.mapIndexed { _, user ->
                        },
                    )
                )
            }
        ),
        message = message,
        state = state,
        status = status
    )
}
