package com.staxxproducts.vehicleservicetracker.data

import androidx.room.*

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicle(vehicle: List<Vehicle>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(service: List<Service>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicleServiceCrossRef(crossRef: VehicleStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM VEHICLE WHERE vehicleId = :vehicleId")
    suspend fun getVehicle(vehicleId: Int): List<Vehicle>

    @Transaction
    @Query("SELECT * FROM SERVICE WHERE serviceId = :serviceId")
    suspend fun getService(serviceId: Int): List<Service>

    @Transaction
    @Query("SELECT * FROM VEHICLESTUDENTCROSSREF WHERE vehicleId = :vehicleId")
    suspend fun getVehicleAndService(vehicleId: Int): List<VehicleStudentCrossRef>
}