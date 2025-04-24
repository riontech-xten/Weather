package com.xtensolutions.weatherapp.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Vaghela Mithun R. on 18/02/21.
 * vaghela.mithun@gmail.com
 */
@Entity(tableName = "BookmarkCity")
data class BookmarkCity(
    @PrimaryKey
    var name: String = "",
    @SerializedName("lat")
    var latitude: Double = 0.0,
    @SerializedName("lon")
    var longitude: Double = 0.0
) : Serializable {
    fun getLatLng(): String {
        return "$latitude, $longitude"
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
