package com.staxxproducts.servicetracker.data.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)){
            return VehicleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}