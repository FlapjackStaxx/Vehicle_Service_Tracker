package com.staxxproducts.vehicleservicetracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.staxxproducts.vehicleservicetracker.db
import com.staxxproducts.vehicleservicetracker.serviceId
import com.staxxproducts.vehicleservicetracker.vehicleId

class ServiceViewModel(var application: Application): ViewModel() {

    private var vehicles: LiveData<List<Vehicle>>? = null

    internal var getService: LiveData<List<Service>> = db.vehicleDao().getService(vehicleId,serviceId)

    internal val getAllVehicles: LiveData<List<Vehicle>> = db.vehicleDao().getAllVehicles()

    internal val getAllVehicleServices: LiveData<List<Service>> = db.vehicleDao().getAllVehicleServices(vehicleId)
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
    fun getService(vehicleId: Long,serviceId: Long){
        db.vehicleDao().getService(vehicleId,serviceId)
    }


    suspend fun getVehicleAndService(vehicleId: Int){
        db.vehicleDao().getVehicleAndService(vehicleId)
    }
    fun deleteByServiceId(service:Service) {
        db.vehicleDao().deleteByServiceId(service)
    }

    fun deleteVehicle(vehicleId: Int){
        db.vehicleDao().deleteVehicle(vehicleId)
    }
}

