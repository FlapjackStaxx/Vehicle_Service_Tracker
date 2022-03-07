package com.staxxproducts.servicetracker.data


import androidx.room.Database

import androidx.room.RoomDatabase
import com.staxxproducts.servicetracker.data.entities.Service
import com.staxxproducts.servicetracker.data.entities.User
import com.staxxproducts.servicetracker.data.entities.Vehicle

@Database(entities = [User::class, Vehicle::class, Service::class,VehicleServiceCrossRef::class], version = 1,
exportSchema = true)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao


}

//    autoMigrations = [AutoMigration(from = 1, to = 2),AutoMigration(from = 2,to = 3),
//                     AutoMigration(from = 3, to = 4)],