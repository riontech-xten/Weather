package com.xtensolutions.weatherapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xtensolutions.weatherapp.data.model.WeatherData
import com.xtensolutions.weatherapp.databinding.RowItem5dayForecastBinding
import com.xtensolutions.weatherapp.databinding.RowItemForecastBinding
import com.xtensolutions.weatherapp.ui.viewholder.BaseViewHolder
import com.xtensolutions.weatherapp.ui.viewholder.ForecastViewHolder


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class ForecastAdapter(val context: Context, var list: MutableList<WeatherData>) :
    RecyclerView.Adapter<ForecastViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    lateinit var unit:String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = RowItem5dayForecastBinding.inflate(inflater, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list.get(position), unit)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}