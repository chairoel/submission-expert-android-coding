package com.mascill.githubapps.detail

import androidx.lifecycle.ViewModel
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase:  UserUseCase) : ViewModel() {
    fun setFavoriteTourism(user: User, newStatus:Boolean) =
        userUseCase.setFavoriteUser(user, newStatus)
}

