package com.beok.common.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<ITEM : Any>(
    @LayoutRes private val layoutRes: Int,
    private val bindingId: Int
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val items = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent = parent, layoutRes = layoutRes, bindingId = bindingId)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun replaceItems(item: List<ITEM>?) {
        if (item.isNullOrEmpty()) return

        items.run {
            clear()
            addAll(item)
        }
    }
}
