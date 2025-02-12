package com.mascill.githubapps.core.data

import com.mascill.githubapps.core.data.source.local.LocalDataSource
import com.mascill.githubapps.core.data.source.remote.RemoteDataSource
import com.mascill.githubapps.core.data.source.remote.network.ApiResponse
import com.mascill.githubapps.core.data.source.remote.response.UserDetailsResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.model.UserDetails
import com.mascill.githubapps.core.domain.repository.IUserRepository
import com.mascill.githubapps.core.utils.AppExecutors
import com.mascill.githubapps.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

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
    override fun getDetailUser(username: String): Flow<Resource<UserDetails>> =
        object : NetworkBoundResource<UserDetails, UserDetailsResponse>(appExecutors) {

            override fun loadFromDB(): Flow<UserDetails> {
                return localDataSource.getUserDetails(username)
                    .map { DataMapper.mapDetailEntityToDomain(it) }
            }

            override fun shouldFetch(data: UserDetails?): Boolean = true

//            override fun shouldFetch(data: UserDetails?): Boolean {
////                val userDetails = data?.let { localDataSource.getUserDetails(it.login) }
////                val result = userDetails?.equals(data) ?: false
////                return !result
//
////                var result = false
////                CoroutineScope(Dispatchers.IO).launch {
////                    val userDetails = data?.let {
////                        localDataSource.getUserDetails(it.login)
////                            .firstOrNull()?.let { entity ->
////                                DataMapper.mapDetailEntityToDomain(entity)
////                            }
////                    }
////                    result = userDetails?.let { it != data } ?: true
////                }
////                return result
//                return true
//            }


            override suspend fun createCall(): Flow<ApiResponse<UserDetailsResponse>> =
                remoteDataSource.getDetailUser(username)

            override suspend fun saveCallResult(data: UserDetailsResponse) {
                val details = DataMapper.mapDetailResponseToEntity(data)
                localDataSource.insertUserDetails(details)
            }

        }.asFlow()


    override fun getUserFavoriteById(id: Long): Flow<User> {
        val userFavoriteById = localDataSource.getUserFavoriteById(id)
        return userFavoriteById.map { DataMapper.mapFavoriteEntityToDomain(it) }
    }

    override suspend fun insertUserFavorite(user: User) {
        val data = DataMapper.mapFavoriteDomainToEntity(user)
        localDataSource.insertUserFavorite(data)
    }

    override suspend fun deleteUserFavoriteById(id: Long) {
        localDataSource.deleteUserFavoriteById(id)
    }

    override fun getUserFavorites(): Flow<List<User>> {
        val data = localDataSource.getUserFavorites()
        return data.map { DataMapper.mapFavoriteEntitiesToDomain(it) }
    }
}