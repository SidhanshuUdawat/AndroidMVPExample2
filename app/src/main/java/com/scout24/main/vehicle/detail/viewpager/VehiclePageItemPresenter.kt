package com.scout24.main.vehicle.detail.viewpager

import com.scout24.R
import com.scout24.datasets.Images

/**
 * Created by Sid on 16/06/2018.
 */

class VehiclePageItemPresenter(val view: VehiclePageItemMvp.View) {

    /**
     * Loads image with the provided url or with a fallback drawable if image has empty url
     */
    fun bind(images: Images) {
        if (images.url.isNotEmpty()) {
            view.loadImage(images.url)
        } else {
            view.hideProgress()
            view.loadImage(R.drawable.no_image_available)
        }
    }

    fun onImageLoadingSuccess() {
        view.hideProgress()
    }

    fun onImageLoadingFailed() {
        view.hideProgress()
    }
}