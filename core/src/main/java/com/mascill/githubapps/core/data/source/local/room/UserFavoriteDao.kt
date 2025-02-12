package com.mascill.githubapps.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import com.mascill.githubapps.core.data.source.local.entity.UserFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserFavoriteDao {
    @Query("SELECT * FROM user_favorite")
    fun getUserFavorites(): Flow<List<UserFavoriteEntity>>

    @Query("SELECT * FROM user_favorite WHERE id = :id")
    fun getUserFavoriteById(id: Long): Flow<UserFavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserFavorite(user: UserFavoriteEntity)

    @Query("DELETE FROM user_favorite WHERE id = :id")
    suspend fun deleteById(id: Long)
}