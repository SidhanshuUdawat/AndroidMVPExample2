package com.scout24.main.vehicle.adapter.vehicle

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleViewHolderMvp {
    interface View {
        fun setTitle(title: String)
        fun setPrice(price: String)
        fun setFuelType(fuelType: String)
        fun setVehicleImage(vehicleImageUrl: String)
        fun setVehicleImage(vehicleImageId: Int)
        fun showProgress()
        fun hideProgress()
    }
}