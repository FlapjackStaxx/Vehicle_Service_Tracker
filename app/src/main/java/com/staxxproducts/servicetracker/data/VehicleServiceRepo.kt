package com.staxxproducts.servicetracker.data

class VehicleServiceRepo(private val vehicleDao: VehicleDao) {

     fun insertVehicle(vehicle: List<Vehicle>) =
        vehicleDao.insertVehicle(vehicle)

    fun insertService(service: List<Service>) =
        vehicleDao.insertService(service)

    fun insertVehicleServiceCrossRef(crossRef: VehicleServiceCrossRef) =
        vehicleDao.insertVehicleServiceCrossRef(crossRef)
}