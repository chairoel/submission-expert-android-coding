package com.mascill.githubapps.core.domain.repository


import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getAllUsers():Flow<Resource<List<User>>>
    fun getDetailUser(username: String):Flow<Resource<UserDetails>>

    fun getUserFavorites(): Flow<List<User>>
    fun getUserFavoriteById(id: Long): Flow<User>
    suspend fun insertUserFavorite(user: User)
    suspend fun deleteUserFavoriteById(id: Long)
}