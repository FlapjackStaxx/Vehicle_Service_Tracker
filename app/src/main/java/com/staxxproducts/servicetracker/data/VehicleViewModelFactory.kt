package com.staxxproducts.servicetracker.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VehicleViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)){
            return VehicleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}