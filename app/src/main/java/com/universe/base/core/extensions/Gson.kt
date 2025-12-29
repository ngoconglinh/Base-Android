package com.universe.base.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> parseJson(json: String): T? {
    val type = object : TypeToken<T>() {}.type
    return try {
        Gson().fromJson(json, type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

inline fun <reified T> T.toJson(): String {
    return try {
        Gson().toJson(this, T::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}