package com.mascill.githubapps.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascill.githubapps.core.data.Resource
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.usecase.user.UserUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.Loading())

            try {
                userUseCase.getAllUser().collect { result ->
                    _users.postValue(result)
                }
            } catch (exception: Exception) {
                _users.postValue(Resource.Error(exception.localizedMessage ?: "Unknown error"))
            }
        }
    }
}
