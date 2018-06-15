package com.scout24.main.vehicle.detail

/**
 * Created by Sid on 15/06/2018.
 */
class VehicleDetailPresenter(private val view: VehicleDetailMvp.View, private val interactor: VehicleDetailMvp.Interactor) {

    fun init() {
        interactor.getVehicle()
    }
}