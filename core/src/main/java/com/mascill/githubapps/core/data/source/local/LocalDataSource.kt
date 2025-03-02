package com.mascill.githubapps.core.data.source.local

import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()
    fun getUserFavorites(): Flow<List<UserEntity>> = userDao.getUserFavorites()
    suspend fun insertUsers(user: List<UserEntity>) = userDao.insertUsers(user)
    fun updateUserFavorite(user: UserEntity, state: Boolean) {
        user.isFavorite = state
        userDao.updateUserFavorite(user)
    }
    fun searchUsers(username: String):Flow<List<UserEntity>> = userDao.searchUsers(username)
}