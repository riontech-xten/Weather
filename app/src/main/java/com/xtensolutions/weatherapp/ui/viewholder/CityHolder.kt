package com.xtensolutions.weatherapp.ui.viewholder

import com.xtensolutions.weatherapp.databinding.RowItemBookmarkBinding
import com.xtensolutions.weatherapp.databinding.RowItemCityBinding
import com.xtensolutions.weatherapp.room.model.BookmarkCity


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class CityHolder(private val binding: RowItemCityBinding) :
    BaseViewHolder(binding.root) {

    fun bind(bookmarkCity: BookmarkCity) {
        binding.city = bookmarkCity
        binding.rowSearchContainer.tag = bookmarkCity
        binding.rowSearchContainer.setOnClickListener(this)
    }

    fun isSelected(selected: Boolean) {
        binding.isSelected = selected
    }
}