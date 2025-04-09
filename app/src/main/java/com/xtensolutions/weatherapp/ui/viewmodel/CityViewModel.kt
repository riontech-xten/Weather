package com.xtensolutions.weatherapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xtensolutions.weatherapp.data.city.CityRepository
import com.xtensolutions.weatherapp.room.model.BookmarkCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */
@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepository: CityRepository) :
    BaseViewModel() {
    fun getCities() = getAssetResult {
        cityRepository.getCities()
    }

    fun getBookmarkedList() = cityRepository.getBookmarkedList()

    fun bookmarkCity(city: BookmarkCity) {
        viewModelScope.launch {
            if (alreadyBookmarked(city).not()) {
                cityRepository.bookmarkCity(city)
            }
        }
    }

    fun removeBookmark(name: String) {
        viewModelScope.launch {
            cityRepository.removeBookmark(name)
        }
    }

    fun resetBookmark() {
        viewModelScope.launch {
            cityRepository.resetBookmark()
        }
    }

    fun bookmarkCities(cities: MutableList<BookmarkCity>): MutableLiveData<Boolean> {
        val statusLiveData = MutableLiveData<Boolean>()
        viewModelScope.launch {
//            for (city in cities) {
//                bookmarkCity(city)
//            }
            cityRepository.bookmarkCities(cities)
            statusLiveData.postValue(true)
        }

        return statusLiveData
    }

    private suspend fun alreadyBookmarked(city: BookmarkCity): Boolean {
        val exist = cityRepository.getBookmarkedCity(city.name)
        return exist != null
    }
}
