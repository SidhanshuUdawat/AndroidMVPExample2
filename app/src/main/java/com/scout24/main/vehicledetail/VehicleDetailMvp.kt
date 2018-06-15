package com.scout24.main.vehicledetail

import com.scout24.datasets.Vehicle
import rx.Observable

/**
 * Created by Sid on 15/06/2018.
 */
interface VehicleDetailMvp {

    interface View {
        fun setTitle(title: String)
        fun setPrice(price: String)
        fun setFuelType(fuelType: String)
    }

    interface Interactor {
        fun getVehicle(): Observable<Vehicle>
    }
}