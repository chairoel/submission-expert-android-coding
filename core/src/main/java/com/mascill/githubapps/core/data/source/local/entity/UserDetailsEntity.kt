package com.mascill.githubapps.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
class UserDetailsEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    val id: Long,

    @field:ColumnInfo(name = "login")
    val login: String,

    @field:ColumnInfo(name = "company")
    val company: String,

    @field:ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @field:ColumnInfo(name = "type")
    val type: String,

    @field:ColumnInfo(name = "url")
    val url: String,

    @field:ColumnInfo(name = "public_repos")
    val publicRepos: Int,

    @field:ColumnInfo(name = "followers")
    val followers: Int,

    @field:ColumnInfo(name = "following")
    val following: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "location")
    val location: String
)