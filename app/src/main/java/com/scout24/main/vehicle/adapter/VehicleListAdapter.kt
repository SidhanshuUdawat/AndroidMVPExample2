package com.scout24.main.vehicle.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scout24.R
import com.scout24.main.vehicle.adapter.vehicle.VehicleViewHolder
import com.scout24.main.vehicle.adapter.vehicle.VehicleViewModel

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleListAdapter(private val list: List<ListItem>, private val listener: OnListInteraction) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VEHICLE = 0
        const val ADVERT = 1
        const val UNKNOWN = 2
    }

    interface OnListInteraction {
        fun onVehicleClicked(vehicleId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VEHICLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_vehicle, parent, false)
                VehicleViewHolder(view, object : VehicleViewHolder.OnVehicleViewHolderInteraction {
                    override fun onVehicleClicked(vehicleId: Int) {
                        listener.onVehicleClicked(vehicleId)
                    }
                })
            }
            ADVERT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_advert, parent, false)
                AdvertViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.empty_silly_view, parent, false)
                EmptyViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is VehicleViewHolder) {
            val model = list[position] as VehicleViewModel
            holder.init(model)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when {
            list[position].itemType == ListItem.ViewType.VEHICLE -> VEHICLE
            list[position].itemType == ListItem.ViewType.ADVERT -> ADVERT
            else -> UNKNOWN
        }
    }

    class AdvertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}