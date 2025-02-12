package com.mascill.githubapps.core.data.source.local

import com.mascill.githubapps.core.data.source.local.entity.UserDetailsEntity
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.local.entity.UserFavoriteEntity
import com.mascill.githubapps.core.data.source.local.room.UserDao
import com.mascill.githubapps.core.data.source.local.room.UserDetailsDao
import com.mascill.githubapps.core.data.source.local.room.UserFavoriteDao
import com.mascill.githubapps.core.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val userDao: UserDao,
    private val userDetailsDao: UserDetailsDao,
    private val userFavoriteDao: UserFavoriteDao
) {

    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()
    suspend fun insertUsers(user: List<UserEntity>) = userDao.insertUsers(user)

    fun getUserDetails(username: String): Flow<UserDetailsEntity> = userDetailsDao.getUserDetails(username)
    suspend fun insertUserDetails(user: UserDetailsEntity) = userDetailsDao.insertUserDetails(user)

    fun getUserFavorites(): Flow<List<UserFavoriteEntity>> = userFavoriteDao.getUserFavorites()
    fun getUserFavoriteById(id: Long): Flow<UserFavoriteEntity> =
        userFavoriteDao.getUserFavoriteById(id)

    suspend fun insertUserFavorite(user: UserFavoriteEntity) =
        userFavoriteDao.insertUserFavorite(user)

    suspend fun deleteUserFavoriteById(id: Long) = userFavoriteDao.deleteById(id)

}