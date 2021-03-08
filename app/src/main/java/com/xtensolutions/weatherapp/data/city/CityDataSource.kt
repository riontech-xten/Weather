package com.xtensolutions.weatherapp.data.city

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xtensolutions.weatherapp.room.dao.BookmarkCityDao
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */
class CityDataSource @Inject constructor(
    @ApplicationContext val context: Context,
    private val bookmarkDao: BookmarkCityDao
) {

    fun loadCitiesFromAsset(): List<BookmarkCity>? {
        var jsonString: String
        var cities: List<BookmarkCity>? = null
        try {
            jsonString = context.assets.open("cities").bufferedReader().use { it.readText() }
            val listLocationType = object : TypeToken<List<BookmarkCity>>() {}.type
            cities = Gson().fromJson(jsonString, listLocationType)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return cities
    }

    fun fileExists(fileName: String): Boolean {
        return listOf(context.assets.list("")).contains(fileName)
    }

    fun getBookmarkedList() = bookmarkDao.getAll()

    suspend fun getBookmarkCity(name: String) = bookmarkDao.getBookmarkCity(name)

    suspend fun bookmarkCity(city: BookmarkCity) = bookmarkDao.bookmarkCity(city)

    suspend fun removeBookmark(name: String) = bookmarkDao.removeBookmark(name)

    suspend fun resetBookmark() = bookmarkDao.resetBookmark()

    suspend fun bookmarkCities(cities: MutableList<BookmarkCity>) = bookmarkDao.bookmarkCities(cities)
}