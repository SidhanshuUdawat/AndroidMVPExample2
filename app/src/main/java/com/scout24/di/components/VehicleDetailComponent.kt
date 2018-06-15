package com.scout24.di.components

import com.scout24.di.modules.VehicleDetailModule
import com.scout24.di.provider.ApplicationBaseComponent
import com.scout24.di.scopes.PerActivity
import com.scout24.main.vehicle.VehicleActivity
import com.scout24.main.vehicle.detail.VehicleDetailActivity
import dagger.Component

/**
 * Created by Sid on 15/06/2018.
 */

@PerActivity
@Component(dependencies = [(ApplicationBaseComponent::class)], modules = [(VehicleDetailModule::class)])
interface VehicleDetailComponent {
    fun inject(vehicleDetailsActivity: VehicleDetailActivity)
}