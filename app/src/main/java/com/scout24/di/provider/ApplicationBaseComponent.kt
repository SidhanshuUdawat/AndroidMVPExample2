package com.scout24.di.provider

import android.app.Application
import android.content.Context
import com.scout24.network.NetworkMonitorProvider
import com.scout24.realm.RealmManager
import com.scout24.sharedpref.SharedPreferencesProvider
import retrofit2.Retrofit

/**
 * Created by Sid on 14/06/2018.
 */

interface ApplicationBaseComponent {

    fun application(): Application

    fun getContext(): Context

    fun getSharedPrefProvider(): SharedPreferencesProvider

    fun getRetrofit(): Retrofit

    fun getRealmManager(): RealmManager

    fun getNetworkMonitor(): NetworkMonitorProvider
}