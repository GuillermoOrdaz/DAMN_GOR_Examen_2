package com.example.gor_examen2.services

import com.example.gor_examen2.entities.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostService {
    @GET("users/{id}/posts")
    suspend fun getPosts(@Path("id") userId: Long): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id:Long): Post

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id:Long, @Body post: Post): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id:Long): Post
}