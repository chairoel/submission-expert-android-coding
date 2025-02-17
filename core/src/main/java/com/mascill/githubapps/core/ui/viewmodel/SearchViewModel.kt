package com.mascill.githubapps.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.usecase.user.UserUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _searchResults = MutableLiveData<List<User>>()
    val searchResults: LiveData<List<User>> get() = _searchResults

    fun searchUsers(username: String) {
        viewModelScope.launch {
            userUseCase.searchUsers(username).collect { users ->
                _searchResults.postValue(users)
            }
        }
    }
}
