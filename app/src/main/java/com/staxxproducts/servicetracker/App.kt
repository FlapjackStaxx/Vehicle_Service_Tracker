package com.staxxproducts.servicetracker

import android.app.Application
import androidx.room.Room
import com.staxxproducts.servicetracker.data.VehicleDatabase

lateinit var db: VehicleDatabase
class App: Application() {
    override fun onCreate() {

        db = Room.databaseBuilder(applicationContext,VehicleDatabase::class.java, "vehicle_db").fallbackToDestructiveMigration()
            .build()
        super.onCreate()
    }
}