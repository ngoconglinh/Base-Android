package com.universe.base.data.model

data class User(
    val id: String,
    val name: String,
    val age: Int,
    val sex: Sex,
    val email: String
) {
    enum class Sex { MALE, FEMALE, NONE }
}
