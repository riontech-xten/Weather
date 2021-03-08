package com.xtensolutions.weatherapp.ui.viewholder

import com.xtensolutions.weatherapp.data.model.WeatherData
import com.xtensolutions.weatherapp.databinding.RowItem5dayForecastBinding
import com.xtensolutions.weatherapp.databinding.RowItemForecastBinding


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */
class ForecastViewHolder(val binding: RowItem5dayForecastBinding) : BaseViewHolder(binding.root) {
    fun bind(data: WeatherData, unit:String) {
        binding.unit = unit
        binding.data = data
    }
}