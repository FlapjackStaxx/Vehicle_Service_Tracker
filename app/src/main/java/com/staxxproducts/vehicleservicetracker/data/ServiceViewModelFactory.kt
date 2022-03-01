package com.staxxproducts.vehicleservicetracker.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ServiceViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceViewModel::class.java)){
            return ServiceViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}