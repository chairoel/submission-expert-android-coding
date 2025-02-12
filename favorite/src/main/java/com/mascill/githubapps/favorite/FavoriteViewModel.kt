package com.mascill.githubapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mascill.githubapps.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase) : ViewModel() {
    val favoriteUser = userUseCase.getFavoriteUser().asLiveData()
}