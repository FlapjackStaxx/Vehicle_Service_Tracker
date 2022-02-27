package com.staxxproducts.vehicleservicetracker.data

import androidx.room.Entity

@Entity(primaryKeys = ["vehicleId","serviceId"])
data class VehicleStudentCrossRef(
    val vehicleId: Long,
    val serviceId: Long
)
