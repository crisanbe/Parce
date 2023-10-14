package com.gerotac.auth.dropdownapi.dropacademicprograms.domain.repository

import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.Town
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface ApisDropRepository {
     fun getAcademicPrograms(token: String) : Flow<Resource<List<Result>>>
     fun getTypeCompany(token: String) : Flow<Resource<List<Result>>>
     fun getLocation(token: String) : Flow<Resource<List<ResultX>>>
     fun getMunicipalityList(token: String) : Flow<Resource<List<Town>>>
}
