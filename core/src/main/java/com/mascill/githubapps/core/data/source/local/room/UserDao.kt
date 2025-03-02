package com.mascill.githubapps.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE is_favorite = 1")
    fun getUserFavorites(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(user: List<UserEntity>)

    @Update
    fun updateUserFavorite(user: UserEntity)

    @Query("SELECT * FROM user WHERE login LIKE '%' || :query || '%'")
    fun searchUsers(query: String): Flow<List<UserEntity>>
}