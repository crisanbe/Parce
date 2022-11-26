package com.gerotac.auth.requirement.data.remote.getrequirement

import com.gerotac.auth.requirement.domain.model.getrequirement.*
import com.gerotac.auth.requirement.domain.model.getrequirement.Data
import com.gerotac.auth.requirement.domain.model.getrequirement.Links
import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination
import com.gerotac.auth.requirement.domain.model.getrequirement.Relations
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.auth.requirement.domain.model.getrequirement.User
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetRequirementResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("data") val `data`: Data,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
) : Serializable

data class Data(
    @SerializedName("links") val links: Links,
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("result") val result: List<Result>? = null
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
    @SerializedName("file") val files: List<File>,
    @SerializedName("interventions") val interventions: List<Intervention>,
    @SerializedName("users") val users: List<User>
) : Serializable

data class Intervention(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val type_intervention: String,
    val user: User
) : Serializable

data class File(
    val created_at: String,
    val filename: String,
    val id: Int,
    val url: String
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
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : Serializable

fun GetRequirementResponse.toGetPagination(): Pagination {
    return Pagination(
        current_page = data.pagination?.current_page,
        from = data.pagination?.from ?: 0,
        last_page = data.pagination?.last_page ?: 0,
        path = data.pagination?.path ?: "pagination",
        per_page = data.pagination?.per_page ?: 0,
        to = data.pagination?.to ?: 0,
        total = data.pagination?.total ?: 0
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
                current_page = data.pagination?.current_page,
                from = data.pagination?.from ?: 0,
                last_page = data.pagination?.last_page ?: 0,
                path = data.pagination?.path ?: "pagination",
                per_page = data.pagination?.per_page ?: 0,
                to = data.pagination?.to ?: 0,
                total = data.pagination?.total ?: 0
            ),
            result = data.result?.mapIndexed { _, result ->
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
                        files = result.relations.files,
                        interventions = result.relations.interventions,
                        users = result.relations.users.mapIndexed { _, user ->
                            com.gerotac.auth.requirement.domain.model.getrequirement.User(
                                name = user.name,
                                role = user.role
                            )
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
