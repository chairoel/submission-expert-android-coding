package com.mascill.githubapps.core.domain.usecase

import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getUserDetails(username: String): Flow<Resource<UserDetails>>
    fun getFavoriteUser(): Flow<List<User>>
    fun getUserFavoriteById(id: Long): Flow<User>
    suspend fun insertUserFavorite(user: User)
    suspend fun deleteUserFavoriteById(id: Long)
}