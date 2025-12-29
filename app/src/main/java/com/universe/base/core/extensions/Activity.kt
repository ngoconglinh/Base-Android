package com.universe.base.core.extensions

import android.app.Activity

fun Activity.isFinishingOrDestroyed(): Boolean = isFinishing || isDestroyed