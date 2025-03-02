package com.mascill.githubapps.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.DetailUser
import com.mascill.githubapps.core.domain.usecase.user.UserUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _detailUser = MutableLiveData<Resource<DetailUser>>()
    val detailUser: LiveData<Resource<DetailUser>> = _detailUser

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            _detailUser.postValue(Resource.Loading())

            try {
                userUseCase.getDetailUsers(username).collect { result ->
                    _detailUser.postValue(result)
                }
            } catch (exception: Exception) {
                _detailUser.postValue(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
        }
    }
}

