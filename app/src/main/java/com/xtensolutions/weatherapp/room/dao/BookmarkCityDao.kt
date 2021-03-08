package com.xtensolutions.weatherapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.xtensolutions.weatherapp.room.model.BookmarkCity


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@Dao
interface BookmarkCityDao {
    @Query("SELECT * FROM BookmarkCity ORDER BY name DESC")
    fun getAll(): LiveData<List<BookmarkCity>>

    @Query("SELECT * FROM BookmarkCity WHERE name = :city")
    suspend fun getBookmarkCity(city: String): BookmarkCity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCity(city: BookmarkCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkCities(cities: MutableList<BookmarkCity>)

    @Query("DELETE FROM BookmarkCity WHERE name = :city ")
    suspend fun removeBookmark(city: String)

    @Query("DELETE FROM BookmarkCity")
    suspend fun resetBookmark()
}