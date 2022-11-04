package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.requirement.domain.model.detailrequirement.*
import com.gerotac.auth.requirement.domain.model.detailrequirement.Areaintervention
import com.gerotac.auth.requirement.domain.model.detailrequirement.User

data class Data(
    @SerializedName("areaintervention") val areaintervention: Areaintervention,
    @SerializedName("cause_problem") val cause_problem: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("description") val description: String,
    @SerializedName("efect_problem") val efect_problem: String,
    @SerializedName("id") val id: Int,
    @SerializedName("impact_problem") val impact_problem: String,
    @SerializedName("relations") val relations: RelationsDto? = null,
    @SerializedName("user") val user: User? = null
)

fun Data.toGetDetail(): DataResponse {
    return DataResponse(
        id = id,
        description = description,
        areaintervention = Areaintervention(name = areaintervention.name),
        cause_problem = cause_problem,
        created_at = created_at,
        efect_problem = efect_problem,
        impact_problem = impact_problem,
        user = user?.let { User(name = it.name, role = user.role) },
        relations = relations?.toGetFile()
    )
}

fun RelationsDto.toGetFile(): Relations {
    return Relations(files = files.map { file -> file.toContenidoFileModel()})
}

fun File.toContenidoFileModel(): FileResponse {
    return FileResponse(
        created_at = created_at,
        id = id,
        url = url,
        downloadedUri = "",
        isDownloading = false
    )
}