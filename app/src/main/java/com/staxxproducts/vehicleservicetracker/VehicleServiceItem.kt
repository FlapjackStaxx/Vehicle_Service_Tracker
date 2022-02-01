package com.staxxproducts.vehicleservicetracker

data class VehicleServiceItem(
    val CarID: String,
    val Make: String,
    val Model: String,
    val Services: ArrayList<Service>,
    val Year: String
)