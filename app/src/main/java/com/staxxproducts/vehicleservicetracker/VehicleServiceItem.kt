package com.staxxproducts.vehicleservicetracker

// Sets up keys and variable types (including a secondary ArrayList for services)
data class VehicleServiceItem(
    val Year: String,
    val Make: String,
    val Model: String,
    val Services: ArrayList<Service>
)