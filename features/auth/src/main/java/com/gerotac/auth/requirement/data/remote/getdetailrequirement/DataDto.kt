package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.gerotac.auth.requirement.domain.model.detailrequirement.Data

data class DataDto(
    val data: DataResult,
    val message: String,
    val status: Boolean
)
fun DataDto.toGetDetail(): Data {
    return Data(
        data = data,
        message = message,
        status = status
    )
}

/*
fun Relations.toGetFile(): com.gerotac.auth.requirement.domain.model.detailrequirement.Relations {
    return  com.gerotac.auth.requirement.domain.model.detailrequirement.Relations(files = files.map { file -> file.toContenidoFileModel()})
}

fun File.toContenidoFileModel(): com.gerotac.auth.requirement.domain.model.detailrequirement.File {
    return com.gerotac.auth.requirement.domain.model.detailrequirement.File(
        created_at = created_at,
        id = id,
        url = url
    )
}*/
