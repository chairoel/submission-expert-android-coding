package com.mascill.githubapps.core.data

import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkResource<ResultType, RequestType> {

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun convertResponseToResult(data: RequestType): ResultType

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val response = createCall().first()) {
            is ApiResponse.Success -> {
                val data = convertResponseToResult(response.data)
                emit(Resource.Success(data))
            }

            is ApiResponse.Empty -> {}

            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
        }
    }

    fun asFlow(): Flow<Resource<ResultType>> = result
}