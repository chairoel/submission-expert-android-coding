package com.mascill.githubapps.core.data.source.remote.network

import com.mascill.githubapps.core.data.source.remote.response.DetailUserResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getAllUsers(): List<UserResponse>

    //    @GET("search/users")
//    suspend fun getSearchUsers(
//        @Query("q") username: String
//    ): Response<SearchUserResponse>
//
    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): DetailUserResponse
//
//    @GET("users/{username}/followers")
//    suspend fun getFollowers(
//        @Path("username") username: String
//    ): Response<List<User>>
//
//    @GET("users/{username}/following")
//    suspend fun getFollowings(
//        @Path("username") username: String
//    ): Response<List<User>>

}
