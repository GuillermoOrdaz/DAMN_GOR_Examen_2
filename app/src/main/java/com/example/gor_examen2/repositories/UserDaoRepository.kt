package com.example.gor_examen2.repositories

import com.example.gor_examen2.data.UserDao
import com.example.gor_examen2.entities.User
import kotlinx.coroutines.flow.Flow

class UserDaoRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) {
        userDao.add(user)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAll()
    }
}