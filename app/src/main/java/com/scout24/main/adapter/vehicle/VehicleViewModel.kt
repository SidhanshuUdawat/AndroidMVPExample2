package com.scout24.main.adapter.vehicle

import com.scout24.main.adapter.ListItem

/**
 * Created by Sid on 14/06/2018.
 */
data class VehicleViewModel(val title: String, val price: Int, val fuelType: String, val vehicleImageUrl: String) : ListItem {
    override val itemType: ListItem.ViewType
        get() = ListItem.ViewType.VEHICLE
}