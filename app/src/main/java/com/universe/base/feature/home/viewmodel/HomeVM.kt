package com.universe.base.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.universe.base.core.ui.UiState
import com.universe.base.data.model.User
import com.universe.base.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeVM(
    private val repo: UserRepository
): ViewModel() {

    private val _users = MutableStateFlow<UiState<List<User>>>(UiState.Idle)
    val users = _users.asStateFlow()

    fun observeUsers() {
        viewModelScope.launch {
            repo.getUsersFlow()
                .onStart { _users.value = UiState.Loading }
                .catch { e ->
                    _users.value = UiState.Error(e)
                }
                .collect { list ->
                    _users.value = UiState.Success(list)
                }
        }
    }

    fun addUser(user: User) = viewModelScope.launch {
        repo.addUser(user)
    }

    fun removeUser(userId: String) = viewModelScope.launch {
        repo.removeUser(userId)
    }

    fun addRandomUser() = viewModelScope.launch {
        repo.addRandomUser()
    }
}
