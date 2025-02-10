package com.mascill.githubapps.core.data

import com.mascill.githubapps.core.data.source.local.LocalDataSource
import com.mascill.githubapps.core.data.source.remote.RemoteDataSource
import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.repository.IUserRepository
import com.mascill.githubapps.core.utils.AppExecutors
import com.mascill.githubapps.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    override fun getAllUsers(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {

            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getAllUsers().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<User>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllUsers()
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }
        }.asFlow()

    override fun getUserFavorites(): Flow<List<User>> {
        return localDataSource.getUserFavorites().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setUserFavorite(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.updateUserFavorite(userEntity, state) }
    }
}