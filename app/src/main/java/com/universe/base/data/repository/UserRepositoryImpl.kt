package com.universe.base.data.repository

import com.universe.base.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class UserRepositoryImpl : UserRepository {

    private val userMap = mutableMapOf<String, User>() //save in RAM

    private val _usersFlow = MutableStateFlow<List<User>>(emptyList())

    override fun getUsersFlow(): Flow<List<User>> = _usersFlow

    override suspend fun getUser(id: String): User? = userMap[id]

    override suspend fun addUser(user: User) {
        userMap[user.id] = user
        _usersFlow.value = userMap.values.toList()
    }

    override suspend fun removeUser(id: String) {
        userMap.remove(id)
        _usersFlow.value = userMap.values.toList()
    }

    override suspend fun addRandomUser() {

    }
}
