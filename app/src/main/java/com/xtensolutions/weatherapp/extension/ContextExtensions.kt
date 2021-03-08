package com.xtensolutions.weatherapp.extension

import android.content.Context
import com.xtensolutions.weatherapp.R


/**
 * Created by Vaghela Mithun R. on 20/02/21.
 * vaghela.mithun@gmail.com
 */

fun Context.isTablet(): Boolean {
    return resources.getBoolean(R.bool.isTablet)
}