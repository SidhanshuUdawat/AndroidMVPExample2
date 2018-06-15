package com.scout24.main.vehicle.adapter.vehicle

import com.scout24.datasets.Vehicle
import com.scout24.main.vehicle.adapter.ListItem

/**
 * Created by Sid on 14/06/2018.
 */

data class VehicleViewModel(val vehicle: Vehicle) : ListItem {
    override val itemType: ListItem.ViewType
        get() = ListItem.ViewType.VEHICLE
}