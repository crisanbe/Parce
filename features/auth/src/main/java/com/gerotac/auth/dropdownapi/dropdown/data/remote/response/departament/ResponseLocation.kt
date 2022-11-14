package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.departament

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Location
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Relations
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Town
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Department
import java.io.Serializable

data class ResponseLocation(
    @SerializedName("code") val code: String,
    @SerializedName("data") val `data`: Data,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
) : Serializable

data class Data(
    @SerializedName("result") val result: List<ResultX>
) : Serializable

data class ResultLocation(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("relations") val relations: Relations
) : Serializable

data class Relations(
    @SerializedName("towns") val towns: List<Town>
) : Serializable

data class Town(
    @SerializedName("department") val department: Department,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : Serializable

data class Department(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : Serializable

/*data class Links(
    val first: String,
    val last: String,
    val next: Any,
    val prev: Any
) : Serializable

data class Pagination(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
) : Serializable*/

fun ResponseLocation.toResponseLocation(): Location {
    return Location(
        code = code,
        data = Data(
            result = data.result.mapIndexed { _, result ->
                ResultX(
                    id = result.id,
                    name = result.name,
                    relations = Relations(
                        towns = result.relations.towns.mapIndexed { _, towns ->
                            Town(
                                id = towns.id,
                                name = towns.name,
                                department = Department(
                                    id = towns.id,
                                    name = towns.name
                                )
                            )

                        }
                    )
                )
            }
        ),
        message = message,
        state = state,
        status = status
    )
}