package com.scout24.main.vehicledetail

/**
 * Created by Sid on 15/06/2018.
 */
interface VehicleDetailMvp {

    interface View {
        fun showSomeDetails()
    }

    interface Interactor {
        fun getVehicle()
    }
}