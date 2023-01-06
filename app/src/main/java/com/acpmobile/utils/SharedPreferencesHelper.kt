package com.acpmobile.utils

import android.content.SharedPreferences
import com.acpmobile.data.model.LoginUser
import com.google.gson.Gson
import java.util.*
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

    fun setList(key: String, list: List<String>) {
        val editor = preferences.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(list)
        editor.putStringSet(key, set)
        editor.apply()
    }

    fun getList(key: String, defaultValue: Set<String>): MutableSet<String>? {
        return preferences.getStringSet(key, defaultValue)
    }

    fun setUserData(userData: LoginUser) {
        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(userData)
        editor.putString(Constants.USER_DATA, json)
        editor.apply()
    }

    fun getUserData(): LoginUser? {
        val gson = Gson()
        val json: String? = preferences.getString(Constants.USER_DATA, "")
        return if (json.isNullOrEmpty()) null else gson.fromJson(json, LoginUser::class.java)
    }
}