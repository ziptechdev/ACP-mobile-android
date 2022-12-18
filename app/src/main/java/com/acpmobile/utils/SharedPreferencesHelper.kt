package com.acpmobile.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(private val preferences: SharedPreferences) {

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue) ?: ""
    }

    fun setString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun setList(key: String, list: List<String>){
        val editor = preferences.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(list)
        editor.putStringSet(key, set)
        editor.apply()
    }

    fun getList(key: String, defaultValue: Set<String>): MutableSet<String>? {
        return preferences.getStringSet(key, defaultValue)
    }
}