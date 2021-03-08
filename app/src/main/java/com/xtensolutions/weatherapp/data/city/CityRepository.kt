package com.xtensolutions.weatherapp.data.city

import com.xtensolutions.weatherapp.room.model.BookmarkCity
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */
class CityRepository @Inject constructor(val cityDataSource: CityDataSource) {
    suspend fun getCities() = cityDataSource.loadCitiesFromAsset()

    fun getBookmarkedList() = cityDataSource.getBookmarkedList()

    suspend fun getBookmarkedCity(name: String) = cityDataSource.getBookmarkCity(name)

    suspend fun bookmarkCity(city: BookmarkCity) = cityDataSource.bookmarkCity(city)

    suspend fun removeBookmark(name: String) = cityDataSource.removeBookmark(name)

    suspend fun resetBookmark() = cityDataSource.resetBookmark()

    suspend fun bookmarkCities(cities: MutableList<BookmarkCity>) =
        cityDataSource.bookmarkCities(cities)
}