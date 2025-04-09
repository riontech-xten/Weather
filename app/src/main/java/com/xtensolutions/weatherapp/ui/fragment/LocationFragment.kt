package com.xtensolutions.weatherapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xtensolutions.weatherapp.ui.viewmodel.LocationViewModel


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */

abstract class LocationFragment : Fragment() {

    protected val locationViewModel: LocationViewModel by viewModels()

    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        if (checkCoarseLocationPermission() && checkFineLocationPermission()) {
            requestUserLocation()
        } else {
            requestPermissionLauncher.launch(locationPermissions)
        }
    }

    private fun requestUserLocation() {
        locationViewModel.locationLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                it.lastLocation?.let { it1 -> onLocationUpdate(it1) }
            }
        })
    }

    @SuppressLint("MissingPermission")
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.get(Manifest.permission.ACCESS_FINE_LOCATION) == true && it.get(Manifest.permission.ACCESS_COARSE_LOCATION) == true)
                requestUserLocation()
        }

    private fun checkFineLocationPermission(): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val res: Int = requireContext().checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    private fun checkCoarseLocationPermission(): Boolean {
        val permission = Manifest.permission.ACCESS_COARSE_LOCATION
        val res: Int = requireContext().checkCallingOrSelfPermission(permission)
        return res == PackageManager.PERMISSION_GRANTED
    }

    abstract fun onLocationUpdate(location: Location)
}
