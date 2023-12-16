package com.georgiopoulos.core.preferences

/**
 * Interface defining methods to interact with SharedPreferences for storing and retrieving preferences.
 */
interface PreferenceStorage {

    /**
     * Returns the value from SharedPreferences for the specified key.
     *
     * @param key The key for the preference.
     * @param defaultValue The default value to be returned if the key is not present.
     * @param <T> The type of the preference value.
     * @return The preference value.
     */
    fun <T> getPreference(key: String, defaultValue: T): T

    /**
     * Sets the value for the specified key in SharedPreferences.
     *
     * @param key The key for the preference.
     * @param value The value to be set for the preference.
     * @param <T> The type of the preference value.
     */
    fun <T> putPreference(key: String, value: T)

    /**
     * Replaces the value for the specified key in SharedPreferences.
     *
     * @param key The key for the preference.
     * @param value The value to be set for the preference.
     * @param <T> The type of the preference value.
     */
    fun <T> replacePreference(key: String, value: T)

    /**
     * Removes the key-value pair from SharedPreferences for the specified key.
     *
     * @param key The key for the preference to be removed.
     */
    fun removePreference(key: String)

    /**
     * Clears all key-value pairs from the entire SharedPreferences.
     */
    fun clearAllPreferences()
}
