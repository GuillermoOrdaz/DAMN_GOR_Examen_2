package com.example.gor_examen2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comments(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
)
