package com.mascill.githubapps.core.utils

import com.mascill.githubapps.core.data.source.local.entity.UserDetailsEntity
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.local.entity.UserFavoriteEntity
import com.mascill.githubapps.core.data.source.remote.response.UserDetailsResponse
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import com.mascill.githubapps.core.domain.model.User
import com.mascill.githubapps.core.domain.model.UserDetails

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type,
                url = it.url
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type,
                url = it.url
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        type = input.type,
        url = input.url
    )

//    fun mapDetailResponseToEntity(input: UserDetailsResponse) = UserDetailsEntity(
//        id = input.id,
//        login = input.login,
//        avatarUrl = input.avatarUrl,
//        type = input.type,
//        url = input.url,
//        name = input.name ?: input.login,
//        followers = input.followers,
//        following = input.following,
//        publicRepos = input.publicRepos,
//        location = input.location ?: "[Location not Set]",
//        company = input.company ?: "[Company not Set]"
//
//
////        login = input.login ?: "",
////        avatarUrl = input.avatarUrl ?: "",
////        type = input.type ?: "",
////        url = input.url ?: "",
////        name = input.name ?: "",
////        followers = input.followers ?: 0,
////        following = input.following ?: 0,
////        publicRepos = input.publicRepos ?: 0,
////        location = input.location ?: "",
////        company = input.company ?: ""
//    )

    fun mapDetailResponseToEntity(input: UserDetailsResponse) = UserDetailsEntity(
        id = input.id ?: 0, // Provide a default value if null
        login = input.login ?: "Unknown", // Default value if null
        avatarUrl = input.avatarUrl ?: "default_url", // Default value if null
        type = input.type ?: "Unknown", // Default value if null
        url = input.url ?: "default_url", // Default value if null
        name = input.name ?: input.login ?: "Unknown", // Default to login if name is null
        followers = input.followers ?: 0, // Default value if null
        following = input.following ?: 0, // Default value if null
        publicRepos = input.publicRepos ?: 0, // Default value if null
        location = input.location ?: "[Location not Set]", // Default value if null
        company = input.company ?: "[Company not Set]" // Default value if null
    )


    fun mapDetailEntityToDomain(input: UserDetailsEntity) = UserDetails(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        type = input.type,
        url = input.url,
        name = input.name,
        followers = input.followers,
        following = input.following,
        publicRepos = input.publicRepos,
        location = input.location,
        company = input.company
    )

    fun mapFavoriteEntitiesToDomain(input: List<UserFavoriteEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type,
                url = it.url
            )
        }

    fun mapFavoriteEntityToDomain(input: UserFavoriteEntity): User =
        User(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl,
            type = input.type,
            url = input.url
        )

    fun mapFavoriteDomainToEntity(input: User) = UserFavoriteEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        type = input.type,
        url = input.url
    )
}