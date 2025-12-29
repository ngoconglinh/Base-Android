package com.universe.base.core.extensions

import android.graphics.Point
import android.view.View


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.getRawView(): Point {
    val location = IntArray(2)
    getLocationOnScreen(location)
    val x = location[0]
    val y = location[1]
    return Point(x, y)
}