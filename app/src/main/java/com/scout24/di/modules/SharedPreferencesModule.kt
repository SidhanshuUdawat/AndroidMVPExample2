package com.scout24.di.modules

import android.content.Context
import com.scout24.sharedpref.MySharedPreferences
import com.scout24.sharedpref.SharedPreferencesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sid on 14/06/2018.
 *
 * Provides single instance of SharedPreferences
 */

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferencesProvider(context: Context): SharedPreferencesProvider {
        val preName = "Scout24Pref"
        return MySharedPreferences(context.getSharedPreferences(preName, Context.MODE_PRIVATE))
    }
}