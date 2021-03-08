package com.xtensolutions.weatherapp.extension

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */

fun Long.toTime(): String {
    return SimpleDateFormat("hh:mm")
        .format(getDateFromMili(this))
}

fun Long.toDate(): String {
    return SimpleDateFormat("EEEE")
        .format(getDateFromMili(this))
}

private fun getDateFromMili(mili: Long): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = mili
//    calendar.timeZone = TimeZone.getDefault()
    return calendar.time
}

