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
 *
 * Fetches the vehicle data and processes it and passes to the View of displaying purpose.
 */

class VehiclePresenter(private val view: VehicleMvp.View, private val interactor: VehicleMvp.Interactor) {

    private val compositeSubscription = CompositeSubscription()

    /**
     * Initial setup
     */
    fun init() {
        getVehicles()
    }

    /**
     * This asks the Interactor for the Vehicle data.
     * Presenter doesn't care from where the data is coming from may it be remote or local.
     */
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

    /**
     * This will be called in 2 scenarios
     * 1. App starts for the first time and there is no internet connection and no local data.
     * 2. Or API sends data which is not suppose to be null.
     */
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

    /**
     * This converts the API Vehicle data model to Vehicle view model.
     */
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
        view.hideError()
        getVehicles()
    }

    fun onVehicleClicked() {

    }

}