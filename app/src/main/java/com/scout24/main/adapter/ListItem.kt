package com.scout24.main.adapter

/**
 * Created by Sid on 14/06/2018.
 */
interface ListItem {

    val itemType: ViewType
    
    enum class ViewType {
        VEHICLE,
        ADVERT
    }
}