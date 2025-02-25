package com.mascill.githubapps.core.data.source.remote

import android.util.Log
import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import com.mascill.githubapps.core.data.source.remote.network.ApiService
import com.mascill.githubapps.core.data.source.remote.response.DetailUserResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUsers(): Flow<ApiResponse<List<UserResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAllUsers()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUsers(username: String): Flow<ApiResponse<DetailUserResponse>>{
        //get data from remote api
        return flow {
            try {
                val response = apiService.getDetailUsers(username)
                if (response.login?.isNotEmpty()!!) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}