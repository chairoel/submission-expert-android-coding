package com.mascill.githubapps.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mascill.githubapps.core.domain.usecase.theme.ThemeUseCase
import kotlinx.coroutines.launch

class ThemeViewModel(private val themeUseCase: ThemeUseCase) : ViewModel() {

    fun getTheme(): LiveData<Boolean> = themeUseCase.getTheme().asLiveData()

    fun saveTheme(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            themeUseCase.saveTheme(isDarkModeActive)
        }
    }
}