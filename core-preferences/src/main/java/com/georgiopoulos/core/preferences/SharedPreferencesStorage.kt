package com.georgiopoulos.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject


internal class SharedPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : PreferenceStorage {

    override fun <T> getPreference(key: String, defaultValue: T): T {
        @Suppress("UNCHECKED_CAST")
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            else -> throw IllegalArgumentException("Unsupported preference type")
        }
    }

    override fun <T> putPreference(key: String, value: T) {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported preference type")
        }
        editor.apply()
    }

    override fun <T> replacePreference(key: String, value: T) {
        removePreference(key)
        putPreference(key, value)
    }

    override fun removePreference(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun clearAllPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
