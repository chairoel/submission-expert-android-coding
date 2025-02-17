package com.mascill.githubapps.core.data.source.remote.network

import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>
}
