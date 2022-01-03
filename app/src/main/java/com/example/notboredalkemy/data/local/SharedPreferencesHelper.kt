package com.example.notboredalkemy.data.local

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceHelper(
    application: Application
) {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

    fun saveCategory(value: Response) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(Constants.KEY_CATEGORY, Gson().toJson(value))
        editor.apply()

    }

    fun getCategory(): Response {
        val data = sharedPreferences.getString(Constants.KEY_CATEGORY, null)
        return Gson().fromJson(data, object : TypeToken<Response>() {}.type)
    }

    fun getValueString(KEY_NAME: String): String? = sharedPreferences.getString(KEY_NAME, null)

    fun getValueInt(KEY_NAME: String): Int = sharedPreferences.getInt(KEY_NAME, 0)

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean = sharedPreferences.getBoolean(KEY_NAME, defaultValue)

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    companion object {
        const val PREFS_NAME = "PREFS_NAME"
    }
}
