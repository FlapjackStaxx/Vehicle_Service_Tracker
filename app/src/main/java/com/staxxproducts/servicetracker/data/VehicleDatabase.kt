package com.staxxproducts.servicetracker.data


import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import java.io.InputStream
import java.io.OutputStream
private const val DB_NAME = "vehicle_db"

@Database(entities = [Vehicle::class,Service::class,VehicleServiceCrossRef::class], version = 1,
exportSchema = true)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao

    companion object {
        fun newInstance(context: Context) =
            Room.databaseBuilder(context, VehicleDatabase::class.java, DB_NAME).build()

        fun exists(context: Context) = context.getDatabasePath(DB_NAME).exists()

        fun copyTo(context: Context, stream: OutputStream) {
            context.getDatabasePath(DB_NAME).inputStream().copyTo(stream)
        }

        fun copyFrom(context: Context, stream: InputStream) {
            val dbFile = context.getDatabasePath(DB_NAME)

            dbFile.delete()

            stream.copyTo(dbFile.outputStream())
        }
    }


}

//    autoMigrations = [AutoMigration(from = 1, to = 2),AutoMigration(from = 2,to = 3),
//                     AutoMigration(from = 3, to = 4)],