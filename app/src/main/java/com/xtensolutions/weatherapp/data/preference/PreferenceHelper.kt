package com.xtensolutions.weatherapp.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Vaghela Mithun R. on 19/02/21.
 * vaghela.mithun@gmail.com
 */
@Singleton
class PreferenceHelper @Inject constructor(@ApplicationContext val context: Context) {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun delete(key: String?) {
        if (sharedPreferences.contains(key)) {
            getEditor().remove(key).commit()
        }
    }

    fun save(key: String?, value: Any?) {
        val editor = getEditor()
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, (value as Int?)!!)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.commit()
    }

    fun <T> get(key: String?): T {
        return sharedPreferences.getAll().get(key) as T
    }

    fun <T> get(key: String?, defValue: T): T {
        return (sharedPreferences.getAll().get(key) ?: defValue) as T
    }

    fun has(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
}