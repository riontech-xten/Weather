package com.xtensolutions.weatherapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.android.gms.common.util.SharedPreferencesUtils
import com.xtensolutions.weatherapp.WeatherApp
import com.xtensolutions.weatherapp.room.WeatherDatabase
import com.xtensolutions.weatherapp.room.dao.BookmarkCityDao
import com.xtensolutions.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    fun provideLogDao(database: WeatherDatabase): BookmarkCityDao {
        return database.bookmarkDao()
    }
}