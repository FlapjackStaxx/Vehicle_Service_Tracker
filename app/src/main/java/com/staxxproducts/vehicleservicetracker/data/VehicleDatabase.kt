package com.staxxproducts.vehicleservicetracker.data


import androidx.room.AutoMigration
import androidx.room.Database

import androidx.room.RoomDatabase

@Database(entities = [Vehicle::class,Service::class,VehicleServiceCrossRef::class], version = 4,autoMigrations = [AutoMigration(from = 1, to = 2),AutoMigration(from = 2, to = 3),AutoMigration(from = 3, to = 4)],
exportSchema = true)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao


}

//    autoMigrations = [AutoMigration(from = 1, to = 2),AutoMigration(from = 2,to = 3),
//                     AutoMigration(from = 3, to = 4)],