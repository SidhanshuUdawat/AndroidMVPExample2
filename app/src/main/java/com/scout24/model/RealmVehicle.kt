package com.scout24.model

import com.scout24.datasets.Images
import com.scout24.datasets.Vehicle
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Sid on 14/06/2018.
 */

open class RealmVehicle(
        var id: Int = 0,
        var make: String = "",
        var model: String = "",
        var modelline: String = "",
        var price: Int = 0,
        var firstRegistration: String = "",
        var mileage: Int = 0,
        var fuel: String = "",
        var seller: RealmSeller? = null,
        var images: RealmList<RealmImage> = RealmList(),
        var description: String = "") : RealmObject() {

    @PrimaryKey
    var mUuid: String = UUID.randomUUID().toString()

    fun asVehicleModel(): Vehicle {
        val images = ArrayList<Images>()
        for (realmImages in this.images) {
            images.add(realmImages.asImageModel())
        }
        return Vehicle(id, make, model, modelline, price, firstRegistration, mileage, fuel, seller?.asSellerModel(), images, description)
    }
}