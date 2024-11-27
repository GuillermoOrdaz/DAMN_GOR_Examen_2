package com.example.gor_examen2.services

import com.example.gor_examen2.entities.Comments
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.entities.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun obtenerUsuarios(): List<User>

    @GET("posts")
    suspend fun obtenerPosts(@Query("userId") userId: Int): List<Post>

    @GET("comments")
    suspend fun obtenerComentarios(@Query("postId") postId: Int): List<Comments>
}