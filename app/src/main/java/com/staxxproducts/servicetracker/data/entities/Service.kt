package com.staxxproducts.servicetracker.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Vehicle::class,
    parentColumns = arrayOf("vehicleId"),
    childColumns = arrayOf("vehicleId"),
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = User::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE)]
)
data class Service(
    @ColumnInfo(name = "userId", index = true)
    val userId: Long?,
    @PrimaryKey(autoGenerate = true)
    val serviceId: Long?,
    @ColumnInfo(name = "vehicleId", index = true)
    val vehicleId: Long?,
    val serviceDate: String,
    val serviceMiles: String,
    val serviceType: String,
    val serviceNotes: String

)
