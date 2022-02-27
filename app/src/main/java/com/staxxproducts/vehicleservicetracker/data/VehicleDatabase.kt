package com.staxxproducts.vehicleservicetracker.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Vehicle::class,Service::class,VehicleStudentCrossRef::class], version = 3,
    autoMigrations = [AutoMigration(from = 1, to = 2),AutoMigration(from = 2,to = 3)], exportSchema = true)
abstract class VehicleDatabase: RoomDatabase() {

    abstract val vehicleDao: VehicleDao
    companion object{
        @Volatile
        private var INSTANCE: VehicleDatabase? = null

        fun getInstance(context: Context): VehicleDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    VehicleDatabase::class.java,
                    "vehicle_db").fallbackToDestructiveMigration()
                    .build().also{
                        INSTANCE = it
                }

            }
        }
    }
}