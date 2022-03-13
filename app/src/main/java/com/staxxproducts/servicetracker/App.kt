package com.staxxproducts.servicetracker

import android.app.Application
import androidx.room.Room
import com.staxxproducts.servicetracker.data.VehicleDatabase
import com.staxxproducts.servicetracker.importexport.MainViewModel
import com.staxxproducts.servicetracker.importexport.DBRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

lateinit var db: VehicleDatabase
class App: Application() {

    private val koinModule = module {
        single(named("appScope")) { CoroutineScope(SupervisorJob()) }
        single { DBRepository(androidContext(), get(named("appScope"))) }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {

        db = Room.databaseBuilder(applicationContext,VehicleDatabase::class.java, "vehicle_db")
            .build()
        super.onCreate()

        startKoin {
          //  androidLogger()
            androidContext(this@App)
            modules(koinModule)
        }

    }
}