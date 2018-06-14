package com.scout24.main

import com.scout24.network.NetworkMonitorProvider

/**
 * Created by Sid on 14/06/2018.
 */
class VehicleInteractor(private val remoteDataSource: VehicleMvp.RemoteDataSource,
                        private val localDataSource: VehicleMvp.LocalDataSource,
                        private val networkMonitorProvider: NetworkMonitorProvider) : VehicleMvp.Interactor {

}