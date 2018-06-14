package com.scout24.sharedpref

/**
 * Created by Sid on 14/06/2018.
 */

interface SharedPreferencesProvider {
    fun putData(key: String, data: Int)
    fun getData(key: String): Int
}