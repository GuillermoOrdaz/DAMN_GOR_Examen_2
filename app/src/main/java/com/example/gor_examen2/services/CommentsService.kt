package com.example.gor_examen2.services

import com.example.gor_examen2.entities.Comments
import com.example.gor_examen2.entities.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommentsService {
    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Long): List<Comments>

    @GET("comments/{id}")
    suspend fun getCommentById(@Path("id") id:Long): Comments

    @POST("comments")
    suspend fun createComment(@Body comments: Comments): Comments

    @PUT("comments/{id}")
    suspend fun updateComment(@Path("id") id:Long, @Body comments: Comments): Comments

    @DELETE("comments/{id}")
    suspend fun deleteComment(@Path("id") id:Long): Comments
}