package com.mascill.githubapps.detail

import androidx.lifecycle.ViewModel
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.usecase.user.UserUseCase

class FavoriteDetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    fun setFavoriteTourism(user: User, newStatus:Boolean) =
        userUseCase.setFavoriteUser(user, newStatus)
}

