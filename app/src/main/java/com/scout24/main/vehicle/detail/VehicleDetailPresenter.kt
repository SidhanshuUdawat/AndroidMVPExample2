package com.scout24.main.vehicle.detail

import com.scout24.datasets.Images
import com.scout24.datasets.Vehicle
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleDetailPresenter(private val view: VehicleDetailMvp.View, private val interactor: VehicleDetailMvp.Interactor) {

    private val compositeSubscription = CompositeSubscription()

    fun init() {
        getVehicleData()
    }

    private fun getVehicleData() {
        compositeSubscription.add(interactor.getVehicle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Vehicle> {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onNext(vehicle: Vehicle) {
                        onFetchingVehicleDataSuccess(vehicle)
                    }
                }))
    }

    fun onFetchingVehicleDataSuccess(vehicle: Vehicle) {
        loadViews(vehicle)
        loadImagePages(vehicle)
    }

    private fun loadImagePages(vehicle: Vehicle) {
        if (vehicle.images != null && vehicle.images.isNotEmpty()) {
            view.addItemsToViewPager(vehicle.images)
        } else {
            val emptyImageItem = Images("")
            val emptyImageList = ArrayList<Images>()
            emptyImageList.add(emptyImageItem)
            view.addItemsToViewPager(emptyImageList)
        }
    }

    private fun loadViews(vehicle: Vehicle) {
        view.setTitle(vehicle.make + " " + vehicle.model)
        view.setPrice("€ " + vehicle.price.toString())
        view.setFuelType(vehicle.fuel)
        view.setMileage(vehicle.mileage.toString())
        view.setDescription(vehicle.description)
    }
}