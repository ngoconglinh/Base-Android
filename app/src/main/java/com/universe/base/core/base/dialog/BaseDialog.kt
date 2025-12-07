package com.universe.base.core.base.dialog

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<VB : ViewBinding>(
    context: Context
) : Dialog(context) {

    protected val binding: VB by lazy { inflateViewBinding() }

    abstract fun inflateViewBinding(): VB

    open val dialogWidth: Int? = null
    open val dialogHeight: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)

        setContentView(binding.root)

        window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            setDimAmount(0.6f)
            val orientation = context.resources.configuration.orientation
            val width = dialogWidth?: if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                (context.resources.displayMetrics.widthPixels * 0.9).toInt()
            } else {
                (context.resources.displayMetrics.widthPixels * 0.6).toInt()
            }
            val height = dialogHeight?: (LinearLayout.LayoutParams.WRAP_CONTENT)
            setLayout(width, height)
        }

    }

}
