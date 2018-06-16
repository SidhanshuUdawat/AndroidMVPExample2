package com.scout24.main.vehicle.detail.viewpager

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.scout24.datasets.Images


/**
 * Created by Sid on 16/06/2018.
 */
class CustomPagerAdapter(private val vehicleImages: List<Images>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val vehiclePageItem = VehiclePageItem(collection.context)
        vehiclePageItem.bind(vehicleImages[position])
        collection.addView(vehiclePageItem)
        return vehiclePageItem
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View?, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int = vehicleImages.size
}