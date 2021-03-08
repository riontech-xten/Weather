package com.xtensolutions.weatherapp.ui.viewmodel

import android.location.Location
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeglo.gowhereuser.location.LocationRepository
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class LocationViewModel @ViewModelInject constructor(val locationRepository: LocationRepository) :
    ViewModel() {
    var locationLiveData: MutableLiveData<LocationResult> = MutableLiveData()

    init {
        setLocationOnTaskCompleted()
        requestLocationUpdate()
    }

    fun requestLocationUpdate() {
        locationRepository.requestLocationUpdate(object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)
                locationLiveData.value = result
                locationRepository.removeLocationUpdate(this)
            }
        })
    }

    private fun setLocationOnTaskCompleted() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        locationRepository.getLastLocation()!!.addOnSuccessListener(executor, { location ->
            locationLiveData.postValue(LocationResult.create(listOf(location)))
        })
    }

    fun getAddress(location: Location) = locationRepository.getLocalAddress(location)
}