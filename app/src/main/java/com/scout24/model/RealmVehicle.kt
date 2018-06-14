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
open class RealmVehicle(vehicle: Vehicle) : RealmObject() {

    @PrimaryKey
    open var mUuid: String = UUID.randomUUID().toString()
    open var id: Int = 0
    open var make: String = ""
    open var model: String = ""
    open var modelline: String = ""
    open var price: Int = 0
    open var firstRegistration: String = ""
    open var mileage: Int = 0
    open var fuel: String = ""
    open lateinit var seller: RealmSeller
    open lateinit var images: RealmList<RealmImage>
    open var description: String = ""

    init {
        id = vehicle.id
        make = vehicle.make
        model = vehicle.model
        if (vehicle.modelline != null) {
            modelline = vehicle.modelline
        }
        price = vehicle.price
        if (vehicle.firstRegistration != null) {
            firstRegistration = vehicle.firstRegistration
        }
        mileage = vehicle.mileage
        fuel = vehicle.fuel
        if (vehicle.seller != null) {
            seller = RealmSeller(vehicle.seller)
        }
        if (vehicle.images != null && vehicle.images.isNotEmpty()) {
            images = RealmList()
            for (image in vehicle.images) {
                images.add(RealmImage(image.url))
            }
        }
    }

    fun asVehicleModel(): Vehicle {
        val images = ArrayList<Images>()
        for (realmImages in this.images) {
            images.add(realmImages.asImageModel())
        }
        return Vehicle(id, make, model, modelline, price, firstRegistration, mileage, fuel, seller.asSellerModel(), images, description)
    }
}