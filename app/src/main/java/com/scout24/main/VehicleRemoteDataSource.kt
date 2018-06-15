package com.scout24.main

import com.scout24.api.VehicleRequestsInterface
import com.scout24.datasets.Vehicle
import rx.Observable

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleRemoteDataSource(private val api: VehicleRequestsInterface) : VehicleMvp.RemoteDataSource {

    override fun getVehicles(): Observable<List<Vehicle>> {
        return api.getVehicleList()
    }
}