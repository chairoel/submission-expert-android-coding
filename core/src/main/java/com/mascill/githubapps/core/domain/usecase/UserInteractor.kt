package com.mascill.githubapps.core.domain.usecase

import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {

    override fun getAllUser() = userRepository.getAllUsers()
    override fun getUserDetails(username: String) = userRepository.getDetailUser(username)

    override fun getFavoriteUser() = userRepository.getUserFavorites()
    override fun getUserFavoriteById(id: Long): Flow<User> = userRepository.getUserFavoriteById(id)
    override suspend fun insertUserFavorite(user: User) = userRepository.insertUserFavorite(user)
    override suspend fun deleteUserFavoriteById(id: Long) =
        userRepository.deleteUserFavoriteById(id)
}