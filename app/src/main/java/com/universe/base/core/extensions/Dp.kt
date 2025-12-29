package com.universe.base.core.extensions
import android.content.res.Resources
import kotlin.math.roundToInt

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()
val Int.dpFloat: Float get() = this * Resources.getSystem().displayMetrics.density