package com.scout24.sharedpref

/**
 * Created by Sid on 14/06/2018.
 */

interface SharedPreferencesProvider {
    fun putBooleanData(key: String, value: Boolean)
    fun getBooleanData(key: String): Boolean
}