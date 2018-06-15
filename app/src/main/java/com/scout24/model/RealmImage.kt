package com.scout24.model

import com.scout24.datasets.Images
import io.realm.RealmObject

/**
 * Created by Sid on 14/06/2018.
 */

open class RealmImage(open var url: String = "") : RealmObject() {
    fun asImageModel(): Images {
        return Images(url)
    }
}