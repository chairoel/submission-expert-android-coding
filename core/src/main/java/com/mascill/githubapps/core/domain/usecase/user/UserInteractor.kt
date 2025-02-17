package com.mascill.githubapps.core.domain.usecase.user

import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.repository.IUserRepository

class UserInteractor (private val userRepository: IUserRepository): UserUseCase {

    override fun getAllUser() = userRepository.getAllUsers()

    override fun getFavoriteUser() = userRepository.getUserFavorites()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setUserFavorite(user, state)
}