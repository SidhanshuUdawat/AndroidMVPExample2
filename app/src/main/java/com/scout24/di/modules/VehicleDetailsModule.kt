package com.scout24.di.modules

import com.scout24.main.vehicle.VehicleLocalDataSource
import com.scout24.main.vehicle.VehicleMvp
import com.scout24.main.vehicle.detail.VehicleDetailInteractor
import com.scout24.main.vehicle.detail.VehicleDetailMvp
import com.scout24.main.vehicle.detail.VehicleDetailPresenter
import com.scout24.realm.RealmManager
import com.scout24.sharedpref.SharedPreferencesProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Sid on 15/06/2018.
 */

@Module
class VehicleDetailsModule(private val view: VehicleDetailMvp.View, private val vehicleId: Int) {

    @Provides
    fun providesLocalDataSource(realmManger: RealmManager, pref: SharedPreferencesProvider): VehicleMvp.LocalDataSource {
        return VehicleLocalDataSource(realmManger, pref)
    }

    @Provides
    fun providesInteractor(localDataSource: VehicleMvp.LocalDataSource): VehicleDetailMvp.Interactor {
        return VehicleDetailInteractor(localDataSource, vehicleId)
    }

    @Provides
    fun providesPresenter(interactor: VehicleDetailMvp.Interactor): VehicleDetailPresenter {
        return VehicleDetailPresenter(view, interactor)
    }
}