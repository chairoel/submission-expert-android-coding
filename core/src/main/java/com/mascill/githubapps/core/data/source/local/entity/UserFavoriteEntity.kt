package com.mascill.githubapps.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_favorite")
class UserFavoriteEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    val id: Long,

    @field:ColumnInfo(name = "login")
    val login: String,

    @field:ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @field:ColumnInfo(name = "type")
    val type: String,

    @field:ColumnInfo(name = "url")
    val url: String,
)