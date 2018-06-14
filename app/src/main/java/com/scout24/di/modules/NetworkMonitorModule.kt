package com.scout24.di.modules

import android.content.Context
import com.scout24.network.LiveNetworkMonitor
import com.scout24.network.NetworkMonitorProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sid on 14/06/2018.
 */

@Module
class NetworkMonitorModule {

    @Provides
    @Singleton
    internal fun provideNetworkMonitorProvider(context: Context): NetworkMonitorProvider {
        return LiveNetworkMonitor(context)
    }
}