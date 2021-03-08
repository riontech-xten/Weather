package com.xtensolutions.weatherapp.room

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.xtensolutions.weatherapp.room.dao.BookmarkCityDao
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Created by Vaghela Mithun R. on 20/02/21.
 * vaghela.mithun@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class BookmarkCityDaoTest {

    private lateinit var bookmarkDao: BookmarkCityDao
    private lateinit var database: WeatherDatabase

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, WeatherDatabase::class.java
        ).build()
        bookmarkDao = database.bookmarkDao()
    }

    @Test
    @Throws(Exception::class)
    fun testAddToBookmark() = runBlocking {
        val city = BookmarkCity("Ahmedabad")
        bookmarkDao.bookmarkCity(city)

        val dbCity = bookmarkDao.getBookmarkCity(city.name)

        assertNotNull(dbCity)
        assertEquals(city.name, dbCity.name)
    }

    @Test
    @Throws(Exception::class)
    fun testRemoveBookmark() = runBlocking {
        val city = BookmarkCity("Ahmedabad")
        bookmarkDao.bookmarkCity(city)

        val dbCity = bookmarkDao.getBookmarkCity(city.name)

        assertNotNull(dbCity)
        assertEquals(city.name, dbCity.name)

        bookmarkDao.removeBookmark(city.name)

        val deleted = bookmarkDao.getBookmarkCity(city.name)
        assertEquals(deleted, null)
    }

    @Test
    @Throws(Exception::class)
    fun testResetBookmarkList() = runBlocking {
        // insert list of data
        bookmarkDao.bookmarkCities(ArrayList<BookmarkCity>().apply {
            add(BookmarkCity("Ahmedabad"))
            add(BookmarkCity("Porbandar"))
            add(BookmarkCity("Vadodara"))
        })

        // clear list
        bookmarkDao.resetBookmark()

        // verify by getting item
        val deleted = bookmarkDao.getBookmarkCity("Ahmedabad")
        assertEquals(deleted, null)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }
}