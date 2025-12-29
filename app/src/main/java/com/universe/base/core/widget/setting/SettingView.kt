package com.universe.base.core.widget.setting

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.universe.base.R
import com.universe.base.core.extensions.gone
import com.universe.base.core.extensions.setOnSingleClick
import com.universe.base.core.extensions.visible
import com.universe.base.databinding.LayoutSettingItemBinding

class SettingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val viewBinding = LayoutSettingItemBinding.inflate(LayoutInflater.from(context), this, true)

    private var swChange: ((Boolean, Boolean) -> Unit)? = null
    private var onItemClicked: (() -> Unit)? = null

    fun onItemClicked(onItemClicked: (() -> Unit)) {
        this.onItemClicked = onItemClicked
    }

    fun switchListener(swChange: (Boolean, Boolean) -> Unit) {
        this.swChange = swChange
    }

    fun setSwitchStatus(b: Boolean) {
        viewBinding.swDescription.isChecked = b
    }

    fun setTxtDescription(s: String) {
        viewBinding.tvDescription.text = s
    }

    fun setTxtTitle(s: String) {
        viewBinding.tvSettingTitle.text = s
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.SettingView) {
            val title = getString(R.styleable.SettingView_svTitle) ?: "Setting"
            val iconDrawable =
                getDrawable(R.styleable.SettingView_svIcon) ?: ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_launcher_foreground
                )
            val secondaryView = getInt(R.styleable.SettingView_svSecondaryView, 0)
            val secondaryViewTitle =
                getString(R.styleable.SettingView_svSecondaryViewTitle) ?: "Setting 2"
            val timePress = getInt(R.styleable.SettingView_svTimePress, 500)

            viewBinding.apply {
                tvSettingTitle.text = title
                ivIcon.setImageDrawable(iconDrawable)
                when (secondaryView) {
                    1 -> {
                        flDescription.visible()
                        swDescription.visible()
                        tvDescription.gone()
                    }

                    2 -> {
                        flDescription.visible()
                        swDescription.gone()
                        tvDescription.visible()
                    }

                    else -> {
                        flDescription.gone()
                    }
                }
                tvDescription.text = secondaryViewTitle
                swDescription.setOnCheckedChangeListener { p0, b ->
                    swChange?.invoke(p0.isPressed, b)
                }
                root.setOnSingleClick(timePress.toLong()) {
                    onItemClicked?.invoke()
                }
            }
        }
    }
}