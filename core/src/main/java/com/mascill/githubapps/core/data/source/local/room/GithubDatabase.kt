package com.mascill.githubapps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mascill.githubapps.core.data.source.local.entity.UserDetailsEntity
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.local.entity.UserFavoriteEntity

@Database(
    entities = [UserEntity::class, UserDetailsEntity::class, UserFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userDetailsDao(): UserDetailsDao
    abstract fun userFavoriteDao(): UserFavoriteDao

}