package com.example.gor_examen2.repositories

import com.example.gor_examen2.entities.Comments
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.network.ClienteRetrofit
import com.example.gor_examen2.services.CommentsService
import com.example.gor_examen2.services.UserService

class RepositoryComment(private val commentsService: CommentsService = ClienteRetrofit.getInstanciaRetrofitComments) {
    suspend fun getAllComments(postId: Long): List<Comments> {
        return commentsService.getComments(postId)
    }

    suspend fun getCommentById(id:Long): Comments {
        return commentsService.getCommentById(id)
    }

    suspend fun createComment(comments: Comments): Comments {
        return commentsService.createComment(comments)
    }

    suspend fun updateComment(id: Long, comments: Comments): Comments {
        return commentsService.updateComment(id, comments)
    }

    suspend fun deleteComment(id: Long): Comments {
        return commentsService.deleteComment(id)
    }
}