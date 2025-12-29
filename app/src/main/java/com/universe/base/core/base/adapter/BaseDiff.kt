package com.universe.base.core.base.adapter

import androidx.recyclerview.widget.DiffUtil

class BaseDiff<T>(
    private val isSame: (T, T) -> Boolean = {old, new -> old == new },
    private val isContentSame: (T, T) -> Boolean = { o, n -> o == n }
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return isSame(oldItem, newItem)
    }

    override fun areContentsTheSame(
        oldItem: T & Any,
        newItem: T & Any
    ): Boolean {
        return isContentSame(oldItem, newItem)
    }
}
