package com.scout24.main

import com.scout24.main.adapter.ListItem


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

    }

    interface RemoteDataSource {

    }

    interface LocalDataSource {

    }

}