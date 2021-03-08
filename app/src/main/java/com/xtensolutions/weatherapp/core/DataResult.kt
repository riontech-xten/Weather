package com.xtensolutions.weatherapp.core

import com.xtensolutions.weatherapp.R


/**
 * Created by Vaghela Mithun R. on 17/02/21.
 * vaghela.mithun@gmail.com
 */
sealed class DataResult<T>(val data: T? = null, val message: String = "") {
    class Success<T>(result: T) : DataResult<T>(data = result)
    class Loading<T>() : DataResult<T>(message = "Please wait while loading...")
    class Fail<T>(msg: String) : DataResult<T>(message = msg)
}
