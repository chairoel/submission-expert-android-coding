package com.mascill.githubapps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mascill.githubapps.core.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}