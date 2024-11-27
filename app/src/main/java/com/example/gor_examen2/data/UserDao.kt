package com.example.gor_examen2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gor_examen2.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id= :id")
    fun getById(id: Long): Flow<User>

    @Insert
    suspend fun add(user: User):Long

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}