package com.scout24.model

import com.scout24.datasets.Seller
import io.realm.RealmObject
import org.jetbrains.annotations.NotNull

/**
 * Created by Sid on 14/06/2018.
 */
open class RealmSeller(seller: Seller) : RealmObject() {

    open var type: String = ""
    open var phone: String = ""
    open var city: String = ""

    init {
        type = seller.type
        phone = seller.phone
        city = seller.city
    }

    fun asSellerModel(): Seller {
        return Seller(type, phone, city)
    }
}