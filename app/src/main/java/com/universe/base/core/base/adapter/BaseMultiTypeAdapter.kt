package com.universe.base.core.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseMultiTypeAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseMultiTypeAdapter.BaseVH<out ViewBinding>>(diffCallback) {

    abstract fun getViewTypeForItem(item: T, position: Int): Int

    abstract fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewBinding

    abstract fun bind(
        binding: ViewBinding,
        item: T,
        position: Int,
        viewType: Int
    )

    class BaseVH<VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return getViewTypeForItem(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<out ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent, viewType)
        return BaseVH(binding)
    }

    override fun onBindViewHolder(holder: BaseVH<out ViewBinding>, position: Int) {
        val item = getItem(position)
        val viewType = getItemViewType(position)
        bind(holder.binding, item, position, viewType)
    }
}
