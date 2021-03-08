package com.xtensolutions.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xtensolutions.weatherapp.databinding.RowItemBookmarkBinding
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import com.xtensolutions.weatherapp.ui.viewholder.BaseViewHolder
import com.xtensolutions.weatherapp.ui.viewholder.BookmarkCityHolder


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class BookmarkCityAdapter(val context: Context, var cities: MutableList<BookmarkCity>) :
    RecyclerView.Adapter<BookmarkCityHolder>() {
    private val inflater = LayoutInflater.from(context)
    lateinit var viewHolderClickListener: BaseViewHolder.ViewHolderClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkCityHolder {
        val binding = RowItemBookmarkBinding.inflate(inflater, parent, false)
        return BookmarkCityHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkCityHolder, position: Int) {
        holder.setViewClickListener(viewHolderClickListener)
        if (cities.size > 0)
            holder.bind(cities.get(position))
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}