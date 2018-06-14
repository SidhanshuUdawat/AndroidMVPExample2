package com.scout24.main.adapter.vehicle

import android.support.v7.widget.RecyclerView
import android.view.View
import com.scout24.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.list_vehicle.view.*
import java.lang.Exception

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
        itemView.vehicleName.text = title
    }

    override fun setPrice(price: String) {
        itemView.vehiclePrice.text = price
    }

    override fun setFuelType(fuelType: String) {
        itemView.vehicleFuelType.text = fuelType
    }

    override fun setVehicleImage(vehicleImageUrl: String) {
        val imageCorner = itemView.context.resources.getDimension(R.dimen.rounded_image_corners).toInt()
        val transformation = RoundedCornersTransformation(imageCorner, 0)
        Picasso.get()
                .load(vehicleImageUrl)
                .transform(transformation)
                .into(itemView.vehicleImage, object : Callback {
                    override fun onSuccess() {
                        presenter.onImageLoadingSuccess()
                    }

                    override fun onError(e: Exception?) {
                        presenter.onImageLoadingFailed()
                    }

                })
    }

    override fun setVehicleImage(vehicleImageId: Int) {
        itemView.vehicleImage.setImageResource(vehicleImageId)
    }

    override fun showProgress() {
        itemView.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        itemView.progressBar.visibility = View.GONE
    }
}