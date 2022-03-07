package com.staxxproducts.servicetracker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.staxxproducts.servicetracker.data.entities.Service
import com.staxxproducts.servicetracker.data.entities.User
import com.staxxproducts.servicetracker.data.entities.Vehicle

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicle(vehicle: List<Vehicle>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: List<User>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(service: List<Service>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicleServiceCrossRef(crossRef: VehicleServiceCrossRef)

    @Transaction
    @Query("SELECT * FROM VEHICLE WHERE vehicleId = :vehicleId")
    suspend fun getVehicle(vehicleId: Int): List<Vehicle>

    @Transaction
    @Query("SELECT * FROM USER WHERE userId = :userId")
    suspend fun getUser(userId: Long): List<User>

    @Transaction
    @Query("SELECT * FROM SERVICE WHERE vehicleId = :vehicleId and serviceId = :serviceId")
    fun getService(vehicleId: Long, serviceId: Long): LiveData<List<Service>>

    @Transaction
    @Query("SELECT * FROM SERVICE WHERE serviceId = :serviceId")
    fun getServiceList(serviceId: Int): Service

    @Transaction
    @Query("SELECT * FROM USER WHERE userId = :userId")
    fun getUserList(userId: Long): User

    @Transaction
    @Query("SELECT * FROM SERVICE WHERE vehicleId = :vehicleId")
    fun getAllVehicleServices(vehicleId: Long): LiveData<List<Service>>

    @Transaction
    @Query("SELECT * FROM VEHICLESERVICECROSSREF WHERE vehicleId = :vehicleId")
    suspend fun getVehicleAndService(vehicleId: Int): List<VehicleServiceCrossRef>


    @Transaction
    @Query("SELECT * FROM VEHICLE")
    fun getAllVehicles(): LiveData<List<Vehicle>>

    @Transaction
    @Query("SELECT * FROM USER")
    fun getAllUsers(): LiveData<List<User>>

    @Transaction
    @Delete
    fun deleteByServiceId(service: Service)

    @Transaction
    @Delete
    fun deleteVehicleById(vehicle: Vehicle)

    @Transaction
    @Delete
    fun deleteUserById(user: User)

    @Query("DELETE FROM VEHICLE WHERE vehicleId = :vehicleId")
    fun deleteVehicle(vehicleId: Int)

    @Query("DELETE FROM USER WHERE userId = :userId")
    fun deleteUser(userId: Long)
}