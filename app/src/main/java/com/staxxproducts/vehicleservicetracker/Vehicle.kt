package com.staxxproducts.vehicleservicetracker

data class Vehicle(val year: String, val make: String, val model: String) {

    override fun toString() = "$year $make $model"
}