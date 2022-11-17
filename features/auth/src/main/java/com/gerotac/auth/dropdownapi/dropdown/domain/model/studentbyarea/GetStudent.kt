package com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea

data class GetStudent(
    val code: String,
    val data: DataStudentByArea,
    val message: String,
    val state: Boolean,
    val status: String
)