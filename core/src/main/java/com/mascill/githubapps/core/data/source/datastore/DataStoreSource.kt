package com.mascill.githubapps.core.data.source.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.mascill.githubapps.core.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreSource(private val dataStore: DataStore<Preferences>) {

    fun getTheme(): Flow<Boolean> {
        return dataStore.data.map {
            it[Constant.THEME_KEY] ?: false
        }
    }

    suspend fun saveTheme(isDarkModeActive: Boolean) {
        dataStore.edit {
            it[Constant.THEME_KEY] = isDarkModeActive
        }
    }
}