package com.scout24

import com.scout24.di.components.DaggerApplicationComponent
import com.scout24.di.modules.ApiModule
import com.scout24.di.modules.ApplicationModule
import com.scout24.di.modules.RealmManagerModule
import com.scout24.di.modules.SharedPreferencesModule
import io.appflate.restmock.RESTMockServer

/**
 * Created by Sid on 15/06/2018.
 */
class TestApplication : Scout24Application() {

    override fun createAppComponent() {
        val baseUrl = RESTMockServer.getUrl() + "api/"
        appProvider = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule(baseUrl))
                .sharedPreferencesModule(SharedPreferencesModule())
                .realmManagerModule(RealmManagerModule())
                .build()
    }
}