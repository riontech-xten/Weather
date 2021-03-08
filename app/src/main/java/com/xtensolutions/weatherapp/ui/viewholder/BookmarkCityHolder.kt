package com.xtensolutions.weatherapp.ui.viewholder

import com.xtensolutions.weatherapp.databinding.RowItemBookmarkBinding
import com.xtensolutions.weatherapp.room.model.BookmarkCity


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class BookmarkCityHolder(private val binding: RowItemBookmarkBinding) :
    BaseViewHolder(binding.root) {

    fun bind(bookmarkCity: BookmarkCity) {
        binding.city = bookmarkCity
        binding.btnRemove.tag = bookmarkCity
        binding.btnRemove.setOnClickListener(this)
        binding.rowContainer.tag = bookmarkCity
        binding.rowContainer.setOnClickListener(this)
    }
}