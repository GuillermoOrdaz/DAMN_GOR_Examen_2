package com.example.gor_examen2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gor_examen2.entities.Comments
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao {
    @Query("SELECT * FROM comments")
    fun getAll(): Flow<List<Comments>>

    @Query("SELECT * FROM comments WHERE id= :id")
    fun getById(id: Long): Flow<Comments>

    @Insert
    suspend fun add(comments: Comments):Long

    @Update
    suspend fun update(comments: Comments)

    @Delete
    suspend fun delete(comments: Comments)
}