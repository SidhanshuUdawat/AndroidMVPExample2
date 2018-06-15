package com.scout24.realm

import io.realm.Realm

/**
 * Created by Sid on 14/06/2018.
 */

interface RealmMvp {

    val realm: Realm

    fun closeRealm(realm: Realm?)

    fun clearData()

    fun init()
}
