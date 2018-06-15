package com.scout24

import android.app.Application
import com.scout24.di.components.DaggerApplicationComponent
import com.scout24.di.modules.ApiModule
import com.scout24.di.modules.ApplicationModule
import com.scout24.di.modules.RealmManagerModule
import com.scout24.di.modules.SharedPreferencesModule
import com.scout24.di.provider.ApplicationBaseComponent

/**
 * Created by Sid on 14/06/2018.
 *
 * Application perform the initial setup of singleton components and they are
 * 1. Application Module provides context to underlying dependencies
 * 2. Api module - Provides retrofit instance
 * 3. SharedPref module - Provides SharedPreferences
 * 4. Realm module - Local storage (Replacement of SQLite)
 */

open class Scout24Application : Application() {

    lateinit var appProvider: ApplicationBaseComponent

    override fun onCreate() {
        super.onCreate()
        createAppComponent()
        setupRealm()
    }

    /**
     * Creates application component and inject all the required dependencies
     */
    open fun createAppComponent() {
        appProvider = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .apiModule(ApiModule("http://private-fe87c-simpleclassifieds.apiary-mock.com/"))
                .sharedPreferencesModule(SharedPreferencesModule())
                .realmManagerModule(RealmManagerModule())
                .build()
    }

    /**
     * Initialising Realm
     */
    private fun setupRealm() {
        getApplicationComponent().getRealmManager().init()
    }

    /**
     * Application component provider
     */
    fun getApplicationComponent(): ApplicationBaseComponent {
        return appProvider
    }
}