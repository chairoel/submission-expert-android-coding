package com.mascill.githubapps.core.domain.usecase.user

import android.util.Log
import com.google.gson.Gson
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.DetailUser
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {

    override fun getAllUser() = userRepository.getAllUsers()

    override fun getDetailUsers(username: String): Flow<Resource<DetailUser>> {
        return userRepository.getDetailUsers(username)
    }

    override fun getFavoriteUser() = userRepository.getUserFavorites()

    override fun setFavoriteUser(user: User, state: Boolean) =
        userRepository.setUserFavorite(user, state)

    override fun searchUsers(username: String) = userRepository.searchUsers(username)
}