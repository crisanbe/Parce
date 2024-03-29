package com.gerotac.auth.dropdownapi.dropdown.domain.repository

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea
import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Town
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResultAcademic
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany.ResultCompany
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface ApisDropRepository {
    fun getAcademicPrograms(token: String): Flow<Resource<List<ResultAcademic>>>
    fun getTypeCompany(token: String): Flow<Resource<List<ResultCompany>>>
    fun getLocation(token: String): Flow<Resource<List<ResultX>>>
    fun getMunicipalityList(token: String): Flow<Resource<List<Town>>>
    fun getTeacherList(token: String): Flow<Resource<List<ResultTeacher>>>
    fun getAreasInterventionList(token: String): Flow<Resource<List<ResultArea>>>
    fun getStudentByArea(token: String, requierementId: Int): Flow<Resource<List<ResultStudent>>>
}
