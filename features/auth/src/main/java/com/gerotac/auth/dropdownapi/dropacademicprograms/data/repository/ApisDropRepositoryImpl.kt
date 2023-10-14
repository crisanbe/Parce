package com.gerotac.auth.dropdownapi.dropacademicprograms.data.repository

import com.gerotac.auth.dropdownapi.dropacademicprograms.data.remote.api.GetApiDropDown
import com.gerotac.auth.dropdownapi.dropacademicprograms.data.remote.response.departament.toResponseLocation
import com.gerotac.auth.dropdownapi.dropacademicprograms.data.remote.response.responsedrop.toAcademic
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.Town
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApisDropRepositoryImpl @Inject constructor(
    private val api: GetApiDropDown
) :
    ApisDropRepository {
    override fun getAcademicPrograms(token: String): Flow<Resource<List<Result>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doAcademicPrograms(token = token).toAcademic()
            emit(Resource.Success(response.data.result))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

    override fun getTypeCompany(token: String): Flow<Resource<List<Result>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doTypeCompany(token = token).toAcademic()
            emit(Resource.Success(response.data.result))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

    override fun getLocation(token: String): Flow<Resource<List<ResultX>>> = flow{
        emit(Resource.Loading())
        try {
            val response = api.doLocation(token = token).toResponseLocation()
            emit(Resource.Success(response.data.result))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

    override fun getMunicipalityList(token: String): Flow<Resource<List<Town>>> = flow{
        emit(Resource.Loading())
        try {
            val response = api.doLocation(token = token).toResponseLocation().data
            emit(Resource.Success(response.result[0].relations.towns))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }
}
