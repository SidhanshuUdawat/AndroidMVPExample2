package com.scout24.model

import com.scout24.datasets.Seller
import io.realm.RealmObject
import io.realm.annotations.Ignore

/**
 * Created by Sid on 14/06/2018.
 */

open class RealmSeller(var type: String = "",
                       var phone: String = "",
                       var city: String = "") : RealmObject() {

    fun asSellerModel(): Seller {
        return Seller(type, phone, city)
    }
}