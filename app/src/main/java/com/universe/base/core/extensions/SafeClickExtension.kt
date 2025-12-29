package com.universe.base.core.extensions

import android.os.SystemClock
import android.view.View

class SafeClickListener(private val delayTime: Long, private val onEventClick: () -> Unit) : View.OnClickListener {
    private companion object {
        private var lastTimeClicked: Long = 0L
    }

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < delayTime) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onEventClick()
    }
}

fun View.setOnSingleClick(
    delayTime: Long = 1000,
    onEventClick: () -> Unit,
) {
    setOnClickListener(SafeClickListener(delayTime, onEventClick))
}
