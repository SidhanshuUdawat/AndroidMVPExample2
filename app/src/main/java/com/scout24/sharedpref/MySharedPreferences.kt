package com.scout24.sharedpref

import android.content.SharedPreferences

/**
 * Created by Sid on 14/06/2018.
 *
 * Provides Shared Pref capabilities
 */

class MySharedPreferences(private val sharedPreferences: SharedPreferences) : SharedPreferencesProvider {

    /**
     * Inserting data of type int
     */
    override fun putData(key: String, data: Int) {
        sharedPreferences.edit().putInt(key, data).apply()
    }

    /**
     * Reading data of type int
     */
    override fun getData(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

}