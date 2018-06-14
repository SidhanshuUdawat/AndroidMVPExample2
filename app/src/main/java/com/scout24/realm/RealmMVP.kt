package com.scout24.realm

import io.realm.Realm

/**
 * Created by Sid on 14/06/2018.
 */

interface RealmMVP {

    val realm: Realm

    fun closeRealm(realm: Realm)

    fun clearData()

    fun init()
}
