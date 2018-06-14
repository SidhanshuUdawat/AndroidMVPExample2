package com.scout24.di.components

import com.scout24.di.modules.*
import com.scout24.di.provider.ApplicationBaseComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sid on 14/06/2018.
 */

@Singleton
@Component(modules = [ApplicationModule::class, SharedPreferencesModule::class,
    ApiModule::class, RealmManagerModule::class, NetworkMonitorModule::class])
interface ApplicationComponent : ApplicationBaseComponent