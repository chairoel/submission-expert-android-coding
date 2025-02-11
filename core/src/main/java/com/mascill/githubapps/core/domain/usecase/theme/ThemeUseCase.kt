package com.mascill.githubapps.core.domain.usecase.theme

import kotlinx.coroutines.flow.Flow

interface ThemeUseCase {
    fun getTheme():Flow<Boolean>
    suspend fun saveTheme(isDarkModeActive: Boolean)
}