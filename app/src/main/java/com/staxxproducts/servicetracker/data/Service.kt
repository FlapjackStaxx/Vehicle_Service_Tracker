package com.staxxproducts.servicetracker.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Vehicle::class,
    parentColumns = arrayOf("vehicleId"),
    childColumns = arrayOf("vehicleId"),
    onDelete = ForeignKey.CASCADE)]
)
data class Service(
    @PrimaryKey(autoGenerate = true)
    val serviceId: Long?,
    val vehicleId: Long?,
    val serviceDate: String,
    val serviceMiles: String,
    val serviceType: String,
    val serviceNotes: String

)
