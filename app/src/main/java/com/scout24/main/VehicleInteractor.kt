package com.scout24.main

import com.scout24.datasets.Vehicle
import com.scout24.network.NetworkMonitorProvider
import rx.Observable

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleInteractor(private val remoteDataSource: VehicleMvp.RemoteDataSource,
                        private val localDataSource: VehicleMvp.LocalDataSource,
                        private val networkMonitorProvider: NetworkMonitorProvider) : VehicleMvp.Interactor {

    override fun getVehicles(): Observable<List<Vehicle>> {
        return when {
            networkMonitorProvider.isConnected() -> remoteDataSource.getVehicles()
                    .flatMap { vehicles ->
                        localDataSource.deleteLocalData()
                        storeVehicles(vehicles)
                        Observable.just(vehicles)
                    }

            localDataSource.hasLocalData() -> localDataSource.getVehicles()
            else -> Observable.just(null)
        }
    }

    private fun storeVehicles(vehicleList: List<Vehicle>) {
        Observable.from(vehicleList)
                .doOnNext { vehicle ->
                    localDataSource.storeVehicles(vehicle)
                }.subscribe()
    }
}