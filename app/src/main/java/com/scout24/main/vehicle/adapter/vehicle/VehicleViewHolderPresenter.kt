package com.scout24.main.vehicle.adapter.vehicle

import com.scout24.R
import com.scout24.datasets.Vehicle

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleViewHolderPresenter(val view: VehicleViewHolderMvp.View) {

    companion object {
        const val MAX_IMAGE_DIMEN = "640"
    }

    lateinit var vehicle: Vehicle

    fun init(viewModel: VehicleViewModel) {
        vehicle = viewModel.vehicle
        val vehicleImage = getVehicleImageUrl(vehicle)
        if (vehicleImage.isNotEmpty()) {
            view.setVehicleImage(vehicleImage)
        } else {
            showFallBackImage()
        }
        view.setTitle(vehicle.make + " " + vehicle.model)
        view.setPrice("€ " + vehicle.price.toString())
        view.setFuelType(vehicle.fuel)
    }

    /**
     * Api provides list of images to display and their order is not always correct
     * in order to display the most optimum image to display list is filter using dimension as 640
     */
    fun getVehicleImageUrl(vehicle: Vehicle): String {
        return if (vehicle.images != null && vehicle.images.isNotEmpty()) {
            val imageURL = vehicle.images.filter { image -> image.url.contains(MAX_IMAGE_DIMEN) }
            if (imageURL.isNotEmpty()) {
                imageURL[0].url
            } else {
                vehicle.images[0].url
            }
        } else {
            ""
        }
    }

    private fun showFallBackImage() {
        view.setVehicleImage(R.drawable.no_image_available)
        view.hideProgress()
    }

    fun onImageLoadingSuccess() {
        view.hideProgress()
    }

    fun onImageLoadingFailed() {
        view.hideProgress()
    }

    fun onContainerClicked() {
        vehicle.let { vehicle ->
            view.onContainerClicked(vehicle.id)
        }
    }
}