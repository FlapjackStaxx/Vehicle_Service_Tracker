package com.staxxproducts.vehicleservicetracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val vehicleId: Int?,
    val vehicleYear: String,
    val vehicleMake: String,
    val vehicleModel: String

)
