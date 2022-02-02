package com.staxxproducts.vehicleservicetracker

data class VehicleServiceItem(
    val Year: String,
    val Make: String,
    val Model: String,
    val Services: ArrayList<Service>
)