package com.codeglo.gowhereuser.location

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.Task
import java.util.*
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@SuppressLint("MissingPermission")
class LocationDataSource @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder
) {
    private val locationRequest: LocationRequest = LocationRequest()
        .setInterval(INTERVAL)
        .setFastestInterval(FASTEST_INTERVAL)
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

    companion object {
        const val INTERVAL: Long = 5000
        const val FASTEST_INTERVAL: Long = 2000
    }

    // for getting the current location update after every 2 seconds with high accuracy
    fun requestCurrentLocation(callback: LocationCallback) {
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, callback, Looper.myLooper()
        )
    }

    fun getLastLocation(): Task<Location>? {
        return fusedLocationProviderClient.lastLocation
    }

    fun removeLocationUpdate(callback: LocationCallback) {
        fusedLocationProviderClient.removeLocationUpdates(callback)
    }

    /**
     * To get a formatted address as from the given location for eg: Chicago IL
     * @property location current location
     * @property context context of the activity or fragment
     * @return address in a specified format like Chicago, IL
     */
    fun getAddressFromLocation(location: Location): String {
        val spaceRegex = " ".toRegex()
        val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        try {
//            address.forEach {
//                return if (it.adminArea == null) {
//                    it.locality
//                } else {
//                    if (it.adminArea.split(spaceRegex).size >= 2) {
//                        "${it.locality} , ${
//                            it.adminArea.split(spaceRegex).first().first().toUpperCase()
//                        }${it.adminArea.split(spaceRegex).last().first().toUpperCase()}"
//                    } else {
//                        "${it.locality}, ${it.adminArea.substring(0, 2).toUpperCase(Locale.ROOT)}"
//                    }
//                }
//            }
            return address?.get(0)?.locality ?: "N/A"
        } catch (e: Exception) {
            return "N/A"
        }
    }
}
