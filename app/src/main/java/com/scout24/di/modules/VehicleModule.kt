package com.scout24.di.modules

import com.scout24.api.VehicleRequestsInterface
import com.scout24.main.*
import com.scout24.network.NetworkMonitorProvider
import com.scout24.realm.RealmManager
import com.scout24.sharedpref.SharedPreferencesProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Sid on 14/06/2018.
 *
 * Injects all the required dependencies in the designated layers.
 */

@Module
class VehicleModule(val view: VehicleMvp.View) {

    @Provides
    fun providesApi(retrofit: Retrofit): VehicleRequestsInterface {
        return retrofit.create(VehicleRequestsInterface::class.java)
    }

    @Provides
    fun providesRemoteDataSource(api: VehicleRequestsInterface): VehicleMvp.RemoteDataSource {
        return VehicleRemoteDataSource(api)
    }

    @Provides
    fun providesLocalDataSource(realmManger: RealmManager, pref: SharedPreferencesProvider): VehicleMvp.LocalDataSource {
        return VehicleLocalDataSource(realmManger, pref)
    }

    @Provides
    fun providesInteractor(remoteDataSource: VehicleMvp.RemoteDataSource,
                           localDataSource: VehicleMvp.LocalDataSource,
                           networkMonitorProvider: NetworkMonitorProvider): VehicleMvp.Interactor {
        return VehicleInteractor(remoteDataSource, localDataSource, networkMonitorProvider)
    }

    @Provides
    fun providesPresenter(interactor: VehicleMvp.Interactor): VehiclePresenter {
        return VehiclePresenter(view, interactor)
    }
}