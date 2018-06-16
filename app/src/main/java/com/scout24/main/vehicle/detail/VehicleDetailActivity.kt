package com.scout24.main.vehicle.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.scout24.R
import com.scout24.Scout24Application
import com.scout24.datasets.Images
import com.scout24.di.components.DaggerVehicleDetailComponent
import com.scout24.di.modules.VehicleDetailModule
import com.scout24.main.vehicle.detail.viewpager.CustomPagerAdapter
import kotlinx.android.synthetic.main.activity_vehicle_detail.*
import javax.inject.Inject

/**
 * Created by Sid on 15/06/2018.
 */

class VehicleDetailActivity : AppCompatActivity(), VehicleDetailMvp.View {

    lateinit var pageAdapter: CustomPagerAdapter
    lateinit var pageItems: MutableList<Images>

    @Inject
    lateinit var presenter: VehicleDetailPresenter

    companion object {
        private const val VEHICLE_ID = "vehicle_id"

        @JvmStatic
        fun getIntent(context: Context, vehicleId: Int): Intent {
            var mIntent = Intent(context, VehicleDetailActivity::class.java)
            mIntent.putExtra(VEHICLE_ID, vehicleId)
            return mIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_detail)
        setupImagePages()
        injectDependencies()
    }

    private fun injectDependencies() {
        val vehicleId = intent.getIntExtra(VEHICLE_ID, 0)
        val applicationComponent = (applicationContext as Scout24Application).getApplicationComponent()
        DaggerVehicleDetailComponent.builder()
                .applicationBaseComponent(applicationComponent)
                .vehicleDetailModule(VehicleDetailModule(this, vehicleId))
                .build().inject(this)
        presenter.init()
    }

    private fun setupImagePages() {
        pageItems = ArrayList()
        pageAdapter = CustomPagerAdapter(pageItems)
        vehicleViewPager.adapter = pageAdapter
        vehicleViewPager.offscreenPageLimit = 3
    }

    override fun addItemsToViewPager(newPageItems: List<Images>) {
        pageItems.addAll(newPageItems)
        pageAdapter.notifyDataSetChanged()
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

    override fun setMileage(mileage: String) {
        vehicleMileage.text = mileage
    }

    override fun setDescription(description: String) {
        vehicleDescription.text = description
    }

}
