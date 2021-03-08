package com.xtensolutions.weatherapp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
object ImageViewBindAdapter {
    @JvmStatic
    @BindingAdapter("iconUrl")
    fun setImageUrl(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("unit")
    fun setUnitsIcon(view: TextView, unit: String) {
        view.apply {
            text = "${text}${unit}"
        }
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun setUnitsIcon(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}