package com.scout24.datasets

/**
 * Created by Sid on 14/06/2018.
 */
data class Vehicle(
        val id: Int,
        val make: String,
        val model: String,
        val modelline: String?,
        val price: Int,
        val firstRegistration: String?,
        val mileage: Int,
        val fuel: String,
        val seller: Seller?,
        val images: List<Images>?,
        val description: String
)