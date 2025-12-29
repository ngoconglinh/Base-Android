package com.universe.base.core.extensions

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showMessage(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.isOrientation(orientation: Int): Boolean {
    return resources.configuration.orientation == orientation
}

fun Context.isLandscape(): Boolean = isOrientation(Configuration.ORIENTATION_LANDSCAPE)

fun Context.isPortrait(): Boolean = isOrientation(Configuration.ORIENTATION_PORTRAIT)