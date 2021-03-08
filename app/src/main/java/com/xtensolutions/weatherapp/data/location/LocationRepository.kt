package com.codeglo.gowhereuser.location

import android.location.Location
import com.google.android.gms.location.LocationCallback
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
class LocationRepository @Inject constructor(val locationDataSource: LocationDataSource) {

    fun requestLocationUpdate(callback: LocationCallback) =
        locationDataSource.requestCurrentLocation(callback)

    fun removeLocationUpdate(callback: LocationCallback) =
        locationDataSource.removeLocationUpdate(callback)

    fun getLastLocation() = locationDataSource.getLastLocation()

    fun getLocalAddress(location: Location) = locationDataSource.getAddressFromLocation(location)
}