package com.mascill.githubapps.core.utils

import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.remote.response.UserResponse
import com.mascill.githubapps.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl,
                type = it.type,
                url = it.url,
                isFavorite = false
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
                url = it.url,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl,
        type = input.type,
        url = input.url,
        isFavorite = input.isFavorite
    )
}