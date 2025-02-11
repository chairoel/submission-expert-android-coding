package com.mascill.githubapps.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface IThemeRepository {
    fun getTheme(): Flow<Boolean>
    suspend fun saveTheme(isDarkModeActive: Boolean)
}