package com.mascill.githubapps.core.domain.repository


import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.data.source.remote.response.DetailUserResponse
import com.mascill.githubapps.core.domain.model.DetailUser
import com.mascill.githubapps.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getAllUsers():Flow<Resource<List<User>>>

    fun getDetailUsers(username: String):Flow<Resource<DetailUser>>

    fun getUserFavorites(): Flow<List<User>>

    fun setUserFavorite(user: User, state: Boolean)

    fun searchUsers(username: String):Flow<List<User>>
}