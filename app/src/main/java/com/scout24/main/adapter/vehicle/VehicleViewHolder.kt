package com.scout24.main.adapter.vehicle

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Sid on 14/06/2018.
 */
class VehicleViewHolder(itemView: View, val listener: OnVehicleViewHolderInteraction) : RecyclerView.ViewHolder(itemView), VehicleViewHolderMvp.View {

    interface OnVehicleViewHolderInteraction {
        fun onVehicleClicked()
    }

    private val presenter = VehicleViewHolderPresenter(this)

    fun init(repositoryViewModel: VehicleViewModel) {
        presenter.init(repositoryViewModel)
    }

    override fun setTitle(title: String) {

    }

    override fun setPrice(price: String) {

    }

    override fun setFuelType(fuelType: String) {

    }

    override fun setVehicleImage(vehicleImageUrl: String) {

    }

}