package com.xtensolutions.weatherapp.di

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.xtensolutions.weatherapp.WeatherApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class LocationModule {

    @Provides
    fun getApplicationContext(app: WeatherApp): Context {
        return app.applicationContext
    }

    @Provides
    fun getFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun getGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }
}
