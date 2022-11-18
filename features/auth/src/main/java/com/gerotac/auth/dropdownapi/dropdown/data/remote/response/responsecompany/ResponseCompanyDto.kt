package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.responsecompany

import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.departament.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany.DataCompany
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany.ResponseCompany
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany.ResultCompany
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseCompanyDto(
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

fun ResponseCompanyDto.toCompany(): ResponseCompany {
    return ResponseCompany(
        code = code,
        data = DataCompany(
            result = data.result.mapIndexed { _, result ->
                ResultCompany (
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



