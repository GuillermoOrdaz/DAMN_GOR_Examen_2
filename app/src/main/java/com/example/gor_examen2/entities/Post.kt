package com.example.gor_examen2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    val userId: Long,
    val title: String,
    val body: String
)
