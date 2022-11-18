package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.responseacademicprograms

import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.departament.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResponseAcademic
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResponseData
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResultAcademic
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseAcademicProgramDto(
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

fun ResponseAcademicProgramDto.toAcademic(): ResponseAcademic {
    return ResponseAcademic(
        code = code,
        data = ResponseData(
            result = data.result.mapIndexed { _, result ->
                ResultAcademic (
                    id = result.id,
                    name = result.name
                )
            }
        ),
        message = message,
        state = state,
        status = status
    )
}



