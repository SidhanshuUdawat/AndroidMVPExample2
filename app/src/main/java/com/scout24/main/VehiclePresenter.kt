package com.scout24.main

import com.scout24.datasets.Vehicle
import com.scout24.main.adapter.ListItem
import com.scout24.main.adapter.vehicle.VehicleViewModel
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by Sid on 14/06/2018.
 */

class VehiclePresenter(val view: VehicleMvp.View, val interactor: VehicleMvp.Interactor) {

    private val compositeSubscription = CompositeSubscription()

    fun init() {
        getVehicles()
    }

    private fun getVehicles() {
        view.showProgress()
        compositeSubscription.add(interactor.getVehicles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<List<Vehicle>> {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {
                        onFetchingVehiclesFailed()
                    }

                    override fun onNext(vehicles: List<Vehicle>) {
                        onFetchingVehiclesSuccess(vehicles)
                    }
                }))
    }

    fun onFetchingVehiclesFailed() {
        view.hideProgress()
        view.showError()
    }

    fun onFetchingVehiclesSuccess(vehicles: List<Vehicle>) {
        view.hideProgress()
        if (vehicles.isEmpty()) {
            view.showError()
        } else {
            processVehicles(vehicles)
        }
    }

    private fun processVehicles(vehicles: List<Vehicle>) {
        val viewModelList = ArrayList<ListItem>()
        for (vehicle in vehicles) {
            val vehicleViewModel = VehicleViewModel(vehicle)
            viewModelList.add(vehicleViewModel)
        }
        view.updateList(viewModelList)
    }


    fun onDestroy() {

    }

    fun onRetryClicked() {

    }

    fun onVehicleClicked() {

    }

}