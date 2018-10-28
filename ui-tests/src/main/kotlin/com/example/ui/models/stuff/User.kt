package com.exmple.gepur.models.stuff

data class User(val firstName: String,
                val lastName: String,
                val email: String,
                val password: String,
                val username: String = "$firstName $lastName"){
    val userName: String get() = "$firstName $lastName"
}