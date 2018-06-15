package com.scout24.main

import com.scout24.datasets.Vehicle
import com.scout24.model.RealmImage
import com.scout24.model.RealmSeller
import com.scout24.model.RealmVehicle
import com.scout24.realm.RealmManager
import com.scout24.sharedpref.SharedPreferencesProvider
import io.realm.RealmList
import rx.Observable

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleLocalDataSource(private val realmManager: RealmManager, private val pref: SharedPreferencesProvider) : VehicleMvp.LocalDataSource {

    companion object {
        const val IS_LOCAL_DATA_SAVED = "is_local_data_saved"
    }

    override fun hasLocalData(): Boolean {
        return pref.getBooleanData(IS_LOCAL_DATA_SAVED)
    }

    override fun deleteLocalData() {
        val realm = realmManager.realm
        try {
            realm.executeTransaction({
                val realmVehicle = realm.where(RealmVehicle::class.java).findAll()
                realmVehicle?.let {
                    realmVehicle.deleteAllFromRealm()
                }
            })
        } finally {
            realmManager.closeRealm(realm)
        }
    }

    override fun storeVehicles(vehicle: Vehicle) {
        val realmVehicleToStore = createRealmVehicle(vehicle)
        val realm = realmManager.realm
        try {
            realm.executeTransaction({
                val realmVehicle = realm.where(RealmVehicle::class.java).findFirst()
                realmVehicle.let {
                    realm.copyToRealmOrUpdate(realmVehicleToStore)
                }
            })
            pref.putBooleanData(IS_LOCAL_DATA_SAVED, true)
        } finally {
            realmManager.closeRealm(realm)
        }
    }

    override fun getVehicles(): Observable<List<Vehicle>> {
        val vehicleList = ArrayList<Vehicle>()
        val realm = realmManager.realm
        try {
            val realmVehicleList = realm.where(RealmVehicle::class.java)?.findAll()
            realmVehicleList?.let { list ->
                for (vehicle in list) {
                    val createVehicle = vehicle.asVehicleModel()
                    vehicleList.add(createVehicle)
                }
            }
            return Observable.just(vehicleList)
        } finally {
            realmManager.closeRealm(realm)
        }
    }

    private fun createRealmVehicle(vehicle: Vehicle): RealmVehicle {
        val modelline = vehicle.modelline ?: ""
        val firstRegistration = vehicle.firstRegistration ?: ""
        val seller = if (vehicle.seller != null) {
            RealmSeller(vehicle.seller.type, vehicle.seller.phone, vehicle.seller.city)
        } else {
            RealmSeller()
        }

        val images = if (vehicle.images != null && vehicle.images.isNotEmpty()) {
            val images = RealmList<RealmImage>()
            for (image in vehicle.images) {
                images.add(RealmImage(image.url))
            }
            images
        } else {
            RealmList()
        }
        return RealmVehicle(vehicle.id, vehicle.make, vehicle.model,
                modelline, vehicle.price, firstRegistration, vehicle.mileage,
                vehicle.fuel, seller, images, vehicle.description)
    }
}