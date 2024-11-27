package com.example.gor_examen2.repositories

import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.entities.User
import com.example.gor_examen2.network.ClienteRetrofit
import com.example.gor_examen2.services.PostService
import com.example.gor_examen2.services.UserService

class RepositoryPost(private val postService: PostService = ClienteRetrofit.getInstanciaRetrofitPost) {
    suspend fun getAllPosts(userId: Long): List<Post> {
        return postService.getPosts(userId)
    }

    suspend fun getPostById(id:Long): Post {
        return postService.getPostById(id)
    }

    suspend fun createPost(post: Post): Post {
        return postService.createPost(post)
    }

    suspend fun updatePost(id: Long, post: Post): Post {
        return postService.updatePost(id, post)
    }

    suspend fun deletePost(id: Long): Post {
        return postService.deletePost(id)
    }
}