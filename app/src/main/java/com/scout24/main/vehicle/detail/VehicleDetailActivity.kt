package com.scout24.main.vehicle.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scout24.R
import kotlinx.android.synthetic.main.activity_vehicle_detail.*

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleDetailActivity : AppCompatActivity(), VehicleDetailMvp.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)
    }

    override fun setTitle(title: String) {
        vehicleTitle.text = title
    }

    override fun setPrice(price: String) {
        vehiclePrice.text = price
    }

    override fun setFuelType(fuelType: String) {
        vehicleFuelType.text = fuelType
    }

}
