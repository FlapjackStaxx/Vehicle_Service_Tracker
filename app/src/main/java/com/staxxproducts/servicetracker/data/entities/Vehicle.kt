package com.staxxproducts.servicetracker.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE)]
)
data class Vehicle(
    val userId: Long,
    @PrimaryKey(autoGenerate = true)
    val vehicleId: Long?,
    val vehicleYear: String,
    val vehicleMake: String,
    val vehicleModel: String

)
