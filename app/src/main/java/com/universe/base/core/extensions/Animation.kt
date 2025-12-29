package com.universe.base.core.extensions
import android.view.animation.Animation

fun Animation.setListener(
    onStart: (() -> Unit)? = null,
    onEnd: (() -> Unit)? = null,
    onRepeat: (() -> Unit)? = null
): Animation {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation) {
            onStart?.invoke()
        }

        override fun onAnimationEnd(animation: Animation) {
            onEnd?.invoke()
        }

        override fun onAnimationRepeat(animation: Animation) {
            onRepeat?.invoke()
        }
    })
    return this
}