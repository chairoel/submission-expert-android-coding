package com.mascill.githubapps.core.data.source.remote

import android.util.Log
import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import com.mascill.githubapps.core.data.source.remote.network.ApiService
import com.mascill.githubapps.core.data.source.remote.response.UserDetailsResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

//    suspend fun getDetailUser(username: String): Flow<ApiResponse<UserDetailsResponse>> {
//        //get data from remote api
//        return flow {
//            try {
//                val response = apiService.getDetailUser(username)
//                if (response.login.isNullOrEmpty() || response.name.isNullOrEmpty()) {
//                    emit(ApiResponse.Empty)
//                } else {
//                    Log.d("TAG", "getDetailUser:  suksess smasuk")
//                    emit(ApiResponse.Success(response))
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//                Log.e("RemoteDataSource", e.toString())
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    suspend fun getDetailUser(username: String): Flow<ApiResponse<UserDetailsResponse>> {
        return flow {
            try {
                Log.d("RemoteDataSource", "Fetching user details for username: $username")
                val response = apiService.getDetailUser(username)
                Log.d("RemoteDataSource", "API Response received: $response")
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                Log.e("RemoteDataSource", "Error fetching user details: ${e.message}")
                emit(ApiResponse.Error("Failed to fetch user details"))
            }
        }
    }


}