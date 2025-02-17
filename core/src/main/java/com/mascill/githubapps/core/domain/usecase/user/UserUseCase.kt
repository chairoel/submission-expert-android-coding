package com.mascill.githubapps.core.domain.usecase.user

import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getAllUser(): Flow<Resource<List<User>>>
    fun getFavoriteUser(): Flow<List<User>>
    fun setFavoriteUser(user: User, state: Boolean)
    fun searchUsers(username:String): Flow<List<User>>
}