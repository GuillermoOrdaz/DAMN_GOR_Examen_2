package com.example.gor_examen2.services

import com.example.gor_examen2.entities.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id:Long): User

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id:Long, @Body user: User): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id:Long): User
}