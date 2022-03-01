package com.staxxproducts.vehicleservicetracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.staxxproducts.vehicleservicetracker.db

class VehicleViewModel(var application: Application): ViewModel() {

    private var vehicles: LiveData<List<Vehicle>>? = null

/*    fun getAllVehicles(): LiveData<List<Vehicle>> {
        if (vehicles != null) {
            vehicles = db.vehicleDao().getAllVehicles()
        }
        return null!!
    }*/
    internal val getAllVehicles: LiveData<List<Vehicle>> = db.vehicleDao().getAllVehicles()

    fun insertVehicle(vehicle:List<Vehicle>){
        db.vehicleDao().insertVehicle(vehicle)
    }
    fun insertService(service:List<Service>){
        db.vehicleDao().insertService(service)
    }
    fun insertVehicleServiceCrossRef(crossRef: VehicleServiceCrossRef){
        db.vehicleDao().insertVehicleServiceCrossRef(crossRef)
    }
    suspend fun getVehicle(vehicle:Int){
        db.vehicleDao().getVehicle(vehicle)
    }
    suspend fun getService(service:Int){
        db.vehicleDao().getService(service)
    }

    suspend fun getVehicleAndService(vehicleId: Int){
        db.vehicleDao().getVehicleAndService(vehicleId)
    }
}

