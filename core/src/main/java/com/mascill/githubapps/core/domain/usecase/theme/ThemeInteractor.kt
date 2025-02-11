package com.mascill.githubapps.core.domain.usecase.theme

import com.mascill.githubapps.core.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeInteractor(private val themeRepository: IThemeRepository) : ThemeUseCase {
    override fun getTheme(): Flow<Boolean> = themeRepository.getTheme()

    override suspend fun saveTheme(isDarkModeActive: Boolean) =
        themeRepository.saveTheme(isDarkModeActive)
}