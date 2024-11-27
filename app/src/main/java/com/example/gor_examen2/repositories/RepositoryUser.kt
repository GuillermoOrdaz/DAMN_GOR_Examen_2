package com.example.gor_examen2.repositories

import com.example.gor_examen2.entities.User
import com.example.gor_examen2.network.ClienteRetrofit
import com.example.gor_examen2.services.UserService

class RepositoryUser(private val userService: UserService = ClienteRetrofit.getInstanciaRetrofitUser) {
    suspend fun getAllUsuarios(): List<User> {
        return userService.getUsers()
    }

    suspend fun getUsuarioById(id:Long): User{
        return userService.getUserById(id)
    }

    suspend fun createUsuario(user: User): User{
        return userService.createUser(user)
    }

    suspend fun updateUsuario(id: Long, user: User): User{
        return userService.updateUser(id, user)
    }

    suspend fun deleteUsuario(id: Long): User{
        return userService.deleteUser(id)
    }
}