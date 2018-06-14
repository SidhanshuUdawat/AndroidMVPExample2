package com.scout24.main

import com.scout24.datasets.Vehicle
import com.scout24.model.RealmVehicle
import com.scout24.realm.RealmManager
import com.scout24.sharedpref.SharedPreferencesProvider
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
        val realmVehicleToStore = RealmVehicle(vehicle)
        val realm = realmManager.realm
        try {
            realm.executeTransaction({
                val realmVehicle = realm.where(RealmVehicle::class.java).findFirst()
                realmVehicle.let {
                    realm.copyToRealmOrUpdate(realmVehicleToStore)
                }
            })
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
                    //val createVehicle = vehicle.asVehicleModel()
                    //vehicleList.add(createVehicle)
                }
            }
            return Observable.just(vehicleList)
        } finally {
            realmManager.closeRealm(realm)
        }
    }
}