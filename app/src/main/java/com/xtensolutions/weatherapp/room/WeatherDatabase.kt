package com.xtensolutions.weatherapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xtensolutions.weatherapp.room.dao.BookmarkCityDao
import com.xtensolutions.weatherapp.room.model.BookmarkCity


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */

@Database(entities = arrayOf(BookmarkCity::class), version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkCityDao
}