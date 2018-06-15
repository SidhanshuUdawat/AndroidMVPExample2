package com.scout24.di.modules

import android.content.Context
import com.scout24.realm.RealmManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sid on 14/06/2018.
 *
 * Provides single instance of Realm Manager which is a wrapper around realm.
 */

@Module
class RealmManagerModule {

    @Provides
    @Singleton
    internal fun providesRealm(context: Context): RealmManager {
        return RealmManager(context)
    }
}