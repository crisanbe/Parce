package com.gerotac.auth.intervention.getintervention.data.repository

import com.gerotac.auth.intervention.getintervention.data.remote.api.GetInterventionApi
import com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions.toGetDetailRequirementListIntervention
import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Intervention
import com.gerotac.auth.intervention.getintervention.domain.repository.DetailRequirementInterventionRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRequirementInterventionRepositoryImpl @Inject constructor(
    private val api: GetInterventionApi
) : DetailRequirementInterventionRepository {

    override fun getDetailInterventionOfRequirement(token:String): Flow<Resource<List<Intervention>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetInterventionApi(token).toGetDetailRequirementListIntervention()
            emit(Resource.Success(response))
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

    /*override suspend fun getCharacter(id: Int): Result<Character> {
        val response = try {
            api.getCharacter(id)
        } catch (e: Exception) {
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toCharacter())
    }*/
}