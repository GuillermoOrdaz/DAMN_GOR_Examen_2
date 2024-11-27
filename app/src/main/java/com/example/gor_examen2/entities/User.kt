package com.example.gor_examen2.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    //@Embedded(prefix = "address_") val address: Address,
    //@Embedded(prefix = "company_") val company: Company
)
