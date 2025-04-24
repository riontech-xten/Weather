package com.xtensolutions.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xtensolutions.weatherapp.databinding.RowItemBookmarkBinding
import com.xtensolutions.weatherapp.databinding.RowItemCityBinding
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.viewholder.BaseViewHolder
import com.xtensolutions.weatherapp.ui.viewholder.BookmarkCityHolder
import com.xtensolutions.weatherapp.ui.viewholder.CityHolder


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class CityAdapter(val context: Context, var cities: MutableList<BookmarkCity>) :
    SearchableAdapter<BookmarkCity>(cities) {
    private val inflater = LayoutInflater.from(context)
    lateinit var viewHolderClickListener: BaseViewHolder.ViewHolderClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RowItemCityBinding.inflate(inflater, parent, false)
        return CityHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CityHolder) {
            holder.setViewClickListener(viewHolderClickListener)
            holder.bind(getItem(position))
            holder.isSelected(isItemSelected(position))
        }
    }

    override fun searchableValue(position: Int): String {
        return dataList[position].name
    }
}
