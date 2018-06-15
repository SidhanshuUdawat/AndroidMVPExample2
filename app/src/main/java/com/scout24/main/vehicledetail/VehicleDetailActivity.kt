package com.scout24.main.vehicledetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scout24.R

class VehicleDetailActivity : AppCompatActivity(), VehicleDetailMvp.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)
    }

    override fun showSomeDetails() {

    }
}
