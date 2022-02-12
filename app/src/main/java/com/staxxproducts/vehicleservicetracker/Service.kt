package com.staxxproducts.vehicleservicetracker

// Sets up the keys and variable types for vehicle services
data class Service(
    val date: String,
    val mileage: String,
    val serviceNotes: String,
    val typeOfService: String
)