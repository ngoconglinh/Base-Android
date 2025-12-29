package com.universe.base.core.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.widget.SwitchCompat

class CustomSwitch @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SwitchCompat(context, attrs) {

    private var ratio = 1.6f
    private var thumbProgress = 0f
    private var animator: ValueAnimator? = null

    init {
        background = null
        foreground = null
        stateListAnimator = null
        thumbProgress = if (isChecked) 1f else 0f
        setOnCheckedChangeListener { _, isChecked ->
            animateThumb(isChecked)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val thumbWidth = thumbDrawable?.intrinsicWidth ?: 0
        val thumbHeight = thumbDrawable?.intrinsicHeight ?: 0

        if (thumbWidth > 0 && thumbHeight > 0) {
            val newWidth = (thumbWidth * ratio).toInt()
            val newHeight = thumbHeight.coerceAtLeast(suggestedMinimumHeight)

            setMeasuredDimension(
                resolveSize(newWidth, widthMeasureSpec),
                resolveSize(newHeight, heightMeasureSpec)
            )
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        val track = trackDrawable
        val thumb = thumbDrawable ?: return

        track?.setBounds(0, 0, width, height)
        track?.draw(canvas)
        val thumbTop = (height - thumb.intrinsicHeight) / 2
        val travelDistance = width - thumb.intrinsicWidth
        val thumbLeft = (travelDistance * thumbProgress).toInt()
        val thumbRight = thumbLeft + thumb.intrinsicWidth
        val thumbBottom = thumbTop + thumb.intrinsicHeight

        thumb.setBounds(thumbLeft, thumbTop, thumbRight, thumbBottom)
        thumb.draw(canvas)
    }

    private fun animateThumb(toChecked: Boolean) {
        val start = thumbProgress
        val end = if (toChecked) 1f else 0f

        animator?.cancel()
        animator = ValueAnimator.ofFloat(start, end).apply {
            duration = 200 // ms
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                thumbProgress = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun setChecked(checked: Boolean) {
        super.setChecked(checked)
        thumbProgress = if (checked) 1f else 0f
        invalidate()
    }
}
