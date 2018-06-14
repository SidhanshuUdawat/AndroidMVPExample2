package com.scout24.api

import com.scout24.datasets.Vehicle
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Sid on 14/06/2018.
 */
interface VehicleRequestsInterface {

    @GET("/")
    fun getVehicleList(): Observable<Vehicle>
}