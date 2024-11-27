package com.example.gor_examen2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.example.gor_examen2.entities.Address
import com.example.gor_examen2.entities.Comments
//import com.example.gor_examen2.entities.Company
//import com.example.gor_examen2.entities.Geo
import com.example.gor_examen2.entities.Post
import com.example.gor_examen2.entities.User

@Database(entities = [User::class, Post::class, Comments::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun PostDao(): PostDao
    abstract fun CommentsDao(): CommentsDao

    companion object{
        @Volatile
        private var INSTANCIA: UserDatabase?=null

        fun getInstancia(contexto: Context): UserDatabase{
            val tempDB = INSTANCIA

            if (tempDB!=null){
                return tempDB
            }
            synchronized(this){
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    UserDatabase::class.java,
                    "usuarios_api"
                ).build()
                INSTANCIA= instancia
                return instancia
            }
        }
    }
}

