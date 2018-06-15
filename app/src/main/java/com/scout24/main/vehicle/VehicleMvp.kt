package com.scout24.main.vehicle

import com.scout24.datasets.Vehicle
import com.scout24.main.vehicle.adapter.ListItem
import rx.Observable


/**
 * Created by Sid on 14/06/2018.
 */

class VehicleMvp {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showError()
        fun hideError()
        fun resetList()
        fun updateList(list: List<ListItem>)
        fun addItemToList(item: ListItem)
        fun removeItemFromList(item: ListItem)
    }

    interface Interactor {
        fun getVehicles(): Observable<List<Vehicle>>
    }

    interface RemoteDataSource {
        fun getVehicles(): Observable<List<Vehicle>>
    }

    interface LocalDataSource {
        fun hasLocalData(): Boolean
        fun deleteLocalData()
        fun storeVehicles(vehicle: Vehicle)
        fun getVehicles(): Observable<List<Vehicle>>
    }

}