package com.scout24.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scout24.R
import com.scout24.Scout24Application
import com.scout24.di.components.DaggerVehicleComponent
import com.scout24.di.modules.VehicleModule
import com.scout24.main.adapter.ListItem
import com.scout24.main.adapter.RecyclerViewDecorator
import com.scout24.main.adapter.VehicleListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by Sid on 14/06/2018.
 */

class VehicleActivity : AppCompatActivity(), VehicleMvp.View {

    private lateinit var vehicleList: MutableList<ListItem>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var vehicleListAdapter: VehicleListAdapter

    @Inject
    lateinit var presenter: VehiclePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setErrorRetryClickListener()
        setupRepoList()
        injectDependencies()
    }

    private fun injectDependencies() {
        val applicationComponent = (applicationContext as Scout24Application).getApplicationComponent()
        DaggerVehicleComponent.builder()
                .applicationBaseComponent(applicationComponent)
                .vehicleModule(VehicleModule(this))
                .build().inject(this)
        presenter.init()
    }

    fun setupRepoList() {
        vehicleList = ArrayList()

        val listener = object : VehicleListAdapter.OnListInteraction {
            override fun onVehicleClicked() {
                presenter.onVehicleClicked()
            }
        }

        vehicleListAdapter = VehicleListAdapter(vehicleList, listener)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val upMargin = resources.getDimensionPixelSize(R.dimen.vehicle_list_separator)
        vehicleRecyclerView.addItemDecoration(RecyclerViewDecorator(upMargin))
        vehicleRecyclerView.layoutManager = layoutManager
        vehicleRecyclerView.itemAnimator = DefaultItemAnimator()
        vehicleRecyclerView.adapter = vehicleListAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setErrorRetryClickListener() {
        errorRetryBtn.setOnClickListener {
            presenter.onRetryClicked()
        }
    }

    override fun showProgress() {
        progressView.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressView.visibility = View.GONE
    }

    override fun showError() {
        errorView.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorView.visibility = View.GONE
    }

    override fun resetList() {
        val previousSize = vehicleList.size
        vehicleList.clear()
        vehicleListAdapter.notifyItemRangeRemoved(0, previousSize)
    }

    override fun updateList(vehicleViewModelList: List<ListItem>) {
        val previousSize = vehicleList.size
        vehicleList.addAll(previousSize, vehicleViewModelList)
        vehicleListAdapter.notifyItemRangeInserted(previousSize, vehicleViewModelList.size)
    }

    override fun addItemToList(item: ListItem) {
        val oldSize = vehicleList.size
        vehicleList.add(item)
        vehicleListAdapter.notifyItemInserted(oldSize)
    }

    override fun removeItemFromList(item: ListItem) {
        val oldSize = vehicleList.size
        vehicleList.remove(item)
        vehicleListAdapter.notifyItemRemoved(oldSize)
    }
}