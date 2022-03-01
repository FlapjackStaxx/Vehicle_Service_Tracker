package com.staxxproducts.vehicleservicetracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["vehicleId","serviceId"])
data class VehicleServiceCrossRef(
    val vehicleId: Long,
    val serviceId: Long
)
