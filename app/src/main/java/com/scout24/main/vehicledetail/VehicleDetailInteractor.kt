package com.scout24.main.vehicledetail

import com.scout24.datasets.Vehicle
import com.scout24.main.vehicle.VehicleMvp
import rx.Observable


/**
 * Created by Sid on 15/06/2018.
 */

class VehicleDetailInteractor(private val localDataSource: VehicleMvp.LocalDataSource, private val vehicleId: Int) : VehicleDetailMvp.Interactor {

    override fun getVehicle(): Observable<Vehicle> {
        return localDataSource.getVehichle(vehicleId)
    }

}