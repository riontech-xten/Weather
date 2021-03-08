package com.xtensolutions.weatherapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Vaghela Mithun R. on 27/01/21.
 * vaghela.mithun@gmail.com
 */
abstract class SelectableItemAdapter<T>(var items: MutableList<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var selectedItems: MutableList<T> = ArrayList()

    open fun toggleSelection(position: Int) {
        val item = getItem(position)
        toggleSelection(item)
        notifyItemChanged(position)
    }

    private fun toggleSelection(item: T) {
        if (selectedItems!!.contains(item)) {
            selectedItems!!.remove(item)
        } else {
            selectedItems!!.add(item)
        }
    }

    private fun removeSelection(item: T) {
        if (selectedItems!!.contains(item)) {
            selectedItems!!.remove(item)
        }
        notifyDataSetChanged()
    }

    open fun selectItem(position: Int) {
        val item = getItem(position)
        selectItem(item)
    }

    open fun selectItem(item: T) {
        if (selectedItems!!.contains(item)) {
            return
        }
        selectedItems!!.add(item)
        notifyDataSetChanged()
    }

    open fun selectAllItem() {
        selectedItems!!.clear()
        selectedItems!!.addAll(items)
        notifyDataSetChanged()
    }

    open fun getSelectedItems(): Collection<T>? {
        return selectedItems
    }

    open fun isItemSelected(position: Int): Boolean {
        return !selectedItems!!.isEmpty() && selectedItems!!.contains(getItem(position))
    }

    open fun clearSelection() {
        selectedItems!!.clear()
        notifyDataSetChanged()
    }

    open fun getItem(position: Int): T {
        return items.get(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list: MutableList<T>) {
        System.out.println("updated list::${list.size}")
        items = list
        super.notifyDataSetChanged()
    }
}