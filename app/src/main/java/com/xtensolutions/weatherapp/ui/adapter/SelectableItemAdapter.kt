package com.xtensolutions.weatherapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Vaghela Mithun R. on 27/01/21.
 * vaghela.mithun@gmail.com
 */
abstract class SelectableItemAdapter<T>(private var items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedItems: MutableList<T> = ArrayList()

    open fun toggleSelection(position: Int) {
        val item = getItem(position)
        toggleSelection(item)
        notifyItemChanged(position)
    }

    private fun toggleSelection(item: T) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
    }

    private fun removeSelection(item: T) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        }
        notifyItemChanged(items.indexOf(item))
    }

    open fun selectItem(position: Int) {
        val item = getItem(position)
        selectItem(item)
    }

    open fun selectItem(item: T) {
        if (selectedItems.contains(item)) {
            return
        }
        selectedItems.add(item)
        notifyItemChanged(items.indexOf(item))
    }

    open fun selectAllItem() {
        selectedItems.clear()
        selectedItems.addAll(items)
        notifyItemRangeChanged(0, itemCount)
    }

    open fun getSelectedItems(): Collection<T>? {
        return selectedItems
    }

    open fun isItemSelected(position: Int): Boolean {
        return selectedItems.isNotEmpty() && selectedItems.contains(getItem(position))
    }

    open fun clearSelection() {
        selectedItems.clear()
        notifyItemRangeChanged(0, itemCount)
    }

    open fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun updateList(list: MutableList<T>) {
        println("updated list::${list.size}")
        notifyItemRangeRemoved(0, itemCount)
        items.clear()
        items.addAll(list)
        notifyItemRangeInserted(0, items.size)
    }
}
