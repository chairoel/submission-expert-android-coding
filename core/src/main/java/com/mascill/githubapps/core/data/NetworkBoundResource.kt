package com.mascill.githubapps.core.data

import android.util.Log
import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import com.mascill.githubapps.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    Log.d("UserRepository", "API call successful: ${apiResponse.data}")
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Empty -> {
                    Log.d("UserRepository", "API call returned empty response")
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }

                is ApiResponse.Error -> {
                    Log.d("UserRepository", "API call failed: ${apiResponse.errorMessage}")
                    onFetchFailed()
                    emit(
                        Resource.Error(apiResponse.errorMessage)
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}