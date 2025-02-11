package com.mascill.githubapps.core.data

import com.mascill.githubapps.core.data.source.datastore.DataStoreSource
import com.mascill.githubapps.core.domain.repository.IThemeRepository
import kotlinx.coroutines.flow.Flow

class ThemeRepository(
    private val dataStoreSource: DataStoreSource,
) : IThemeRepository {
    override fun getTheme(): Flow<Boolean> = dataStoreSource.getTheme()

    override suspend fun saveTheme(isDarkModeActive: Boolean) =
        dataStoreSource.saveTheme(isDarkModeActive)

}