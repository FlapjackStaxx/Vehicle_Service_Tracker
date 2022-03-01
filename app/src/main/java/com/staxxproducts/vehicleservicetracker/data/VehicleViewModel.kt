package com.staxxproducts.vehicleservicetracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.staxxproducts.vehicleservicetracker.db

class VehicleViewModel(var application: Application): ViewModel() {

    private var vehicles: LiveData<List<Vehicle>>? = null

    internal val getAllVehicles: LiveData<List<Vehicle>> = db.vehicleDao().getAllVehicles()

    fun insertVehicle(vehicle:List<Vehicle>){
         db.vehicleDao().insertVehicle(vehicle)

    }
    fun insertService(service:List<Service>) {
         db.vehicleDao().insertService(service)
    }
    fun insertVehicleServiceCrossRef(crossRef: VehicleServiceCrossRef){
        db.vehicleDao().insertVehicleServiceCrossRef(crossRef)
    }
    suspend fun getVehicle(vehicle:Int){
        db.vehicleDao().getVehicle(vehicle)
    }
    fun deleteVehicleById(vehicle: Vehicle) {
        db.vehicleDao().deleteVehicleById(vehicle)
    }

    suspend fun getVehicleAndService(vehicleId: Int){
        db.vehicleDao().getVehicleAndService(vehicleId)
    }
}

