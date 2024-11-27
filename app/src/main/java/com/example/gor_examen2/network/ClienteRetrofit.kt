package com.example.gor_examen2.network

import com.example.gor_examen2.services.ApiService
import com.example.gor_examen2.services.CommentsService
import com.example.gor_examen2.services.PostService
import com.example.gor_examen2.services.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val getInstanciaRetrofitUser: UserService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(UserService::class.java)
    }

    val getInstanciaRetrofitPost: PostService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(PostService::class.java)
    }

    val getInstanciaRetrofitComments: CommentsService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(CommentsService::class.java)
    }
}