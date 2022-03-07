package com.staxxproducts.servicetracker.data

import com.staxxproducts.servicetracker.data.entities.Service
import com.staxxproducts.servicetracker.data.entities.Vehicle

class VehicleServiceRepo(private val vehicleDao: VehicleDao) {

     fun insertVehicle(vehicle: List<Vehicle>) =
        vehicleDao.insertVehicle(vehicle)

    fun insertService(service: List<Service>) =
        vehicleDao.insertService(service)

    fun insertVehicleServiceCrossRef(crossRef: VehicleServiceCrossRef) =
        vehicleDao.insertVehicleServiceCrossRef(crossRef)
}