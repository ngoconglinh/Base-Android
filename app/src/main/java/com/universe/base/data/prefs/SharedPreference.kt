package com.universe.base.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.Boolean

object SharedPreference {
    lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    var isFirstOpenApp: Boolean
        get() = sharedPref.getBoolean(IS_FIRST_OPEN_KEY, true)
        set(value) =
            sharedPref.edit {
                putBoolean(IS_FIRST_OPEN_KEY, value)
            }

    const val IS_FIRST_OPEN_KEY = "IS_FIRST_OPEN_KEY"
}
