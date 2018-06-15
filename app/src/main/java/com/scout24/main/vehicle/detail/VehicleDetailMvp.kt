package com.scout24.main.vehicle.detail

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
        fun setDescription(description: String)
        fun setMileage(mileage: String)
    }

    interface Interactor {
        fun getVehicle(): Observable<Vehicle>
    }
}