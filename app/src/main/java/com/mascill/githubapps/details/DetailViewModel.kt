package com.mascill.githubapps.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mascill.githubapps.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

//    val tourism = userUseCase.getUserDetails().asLiveData()

    fun getDetails(username: String) =
        userUseCase.getUserDetails(username).asLiveData()


//    private val _detailUser = MutableLiveData<Resource<UserDetails>>()
//    val detailUser: LiveData<Resource<UserDetails>> = _detailUser
//
//    fun getDetailUser(username: String) {
//        viewModelScope.launch {
//            _detailUser.postValue(Resource.Loading())
//
//            try {
//                Log.d("TAG", "getDetailUser: username $username")
//                userUseCase.getUserDetails(username).collect { result ->
//                    _detailUser.postValue(result)
//                    Log.d("TAG", "getDetailUser: result: ")
//                }
//            } catch (exception: Exception) {
//                _detailUser.postValue(Resource.Error(exception.localizedMessage ?: "Unknown error"))
//            }
//        }
//    }
}
