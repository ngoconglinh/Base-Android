package com.universe.base.data.repository

import com.universe.base.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersFlow(): Flow<List<User>>
    suspend fun getUser(id: String): User?
    suspend fun addUser(user: User)
    suspend fun removeUser(id: String)
    suspend fun addRandomUser()
}
