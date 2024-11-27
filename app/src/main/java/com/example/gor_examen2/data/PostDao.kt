package com.example.gor_examen2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gor_examen2.entities.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): Flow<List<Post>>

    @Query("SELECT * FROM post WHERE id= :id")
    fun getById(id: Long): Flow<Post>

    @Insert
    suspend fun add(post: Post):Long

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)
}