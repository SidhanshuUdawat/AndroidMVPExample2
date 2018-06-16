package com.scout24.main.vehicle.detail.viewpager

import android.support.annotation.DrawableRes

/**
 * Created by Sid on 16/06/2018.
 */

interface VehiclePageItemMvp {

    interface View {
        fun loadImage(imageUrl: String)
        fun loadImage(@DrawableRes resourceId: Int)
    }
}