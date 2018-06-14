package com.scout24.main

import com.scout24.api.VehicleRequestsInterface

/**
 * Created by Sid on 14/06/2018.
 */
class VehicleRemoteDataSource(private val api: VehicleRequestsInterface) : VehicleMvp.RemoteDataSource {
}