package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.responsedrop

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.departament.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.ResponseAcademic
import java.io.Serializable
import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.ResponseData

data class ResponseAcademicPrograms(
    @SerializedName("code") val code: String,
    @SerializedName("data") val `data`: Data,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
) : Serializable

data class Data(
    @SerializedName("result") val result: List<Result>
) : Serializable

data class Result(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : Serializable

fun ResponseAcademicPrograms.toAcademic(): ResponseAcademic {
    return ResponseAcademic(
        code = code,
        data = ResponseData(
            result = data.result.mapIndexed { _, result ->
                Result (
                    id = result.id,
                    name = result.name
                )
            }
        ),
        message = message,
        state = state,
        status = status
    )
    /*val result = result.mapIndexed { _, result ->
        Result(
            id = result.id,
            name = result.name
        )
    }
    return result*/
}



