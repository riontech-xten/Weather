package com.xtensolutions.weatherapp.extension

import com.xtensolutions.weatherapp.utils.DateFormatUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */

fun String.utcToLocal(): String {
    val sdf = SimpleDateFormat(DateFormatUtils.DB_FORMAT, Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    val date = sdf.parse(this)
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun String.convertToDay(): String {
    var sdf = SimpleDateFormat(DateFormatUtils.DB_FORMAT, Locale.ENGLISH)
    var date = sdf.parse(this)

    val cal = Calendar.getInstance()
    cal.time = date

    val calNow = Calendar.getInstance()
    calNow.timeInMillis = System.currentTimeMillis()

    return if (calNow.get(Calendar.DATE) == cal.get(Calendar.DATE)) {
        "Today "
    } else if (cal.get(Calendar.DATE) - calNow.get(Calendar.DATE) == 1) {
        "Tomorrow "
    } else {
        sdf.applyPattern("EEEE")
        sdf.format(cal.time)
    }
}

fun String.convertToTime(): String {
    var sdf = SimpleDateFormat(DateFormatUtils.DB_FORMAT, Locale.ENGLISH)
    var date = sdf.parse(this)
    sdf.applyPattern("hh:mm")
    return sdf.format(date)
}