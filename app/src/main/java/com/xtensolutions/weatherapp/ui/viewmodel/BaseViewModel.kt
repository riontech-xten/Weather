package com.xtensolutions.weatherapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.xtensolutions.weatherapp.R
import com.xtensolutions.weatherapp.core.DataResult
import kotlinx.coroutines.flow.flow
import retrofit2.Response


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
open class BaseViewModel @ViewModelInject constructor() : ViewModel() {
    fun <T> getDataResult(call: suspend () -> Response<T>) = flow<DataResult<T>> {
        emit(DataResult.Loading())
        val response = call.invoke()
        if (response.isSuccessful) {
            emit(DataResult.Success(response.body()!!))
        } else {
            emit(DataResult.Fail(response.errorBody().toString()))
        }
    }.asLiveData()

    fun <T> getAssetResult(call: suspend () -> T) = flow<DataResult<T>> {
        emit(DataResult.Loading())
        val response = call.invoke()
        if (response != null) {
            emit(DataResult.Success(response))
        } else {
            emit(DataResult.Fail("No data found"))
        }
    }.asLiveData()
}