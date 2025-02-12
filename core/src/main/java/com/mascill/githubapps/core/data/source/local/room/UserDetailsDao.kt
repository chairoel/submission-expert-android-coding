package com.mascill.githubapps.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mascill.githubapps.core.data.source.local.entity.UserDetailsEntity
import com.mascill.githubapps.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM user_details WHERE login = :username")
    fun getUserDetails(username : String): Flow<UserDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserDetails(user: UserDetailsEntity)

}