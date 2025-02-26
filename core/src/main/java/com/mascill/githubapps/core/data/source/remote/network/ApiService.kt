package com.mascill.githubapps.core.data.source.remote.network

import com.mascill.githubapps.core.data.source.remote.response.DetailUserResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("users/{username}")
    suspend fun getDetailUsers(
        @Path("username") username: String
    ): DetailUserResponse
}
