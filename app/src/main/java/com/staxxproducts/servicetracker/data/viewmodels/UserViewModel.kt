package com.staxxproducts.servicetracker.data.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.staxxproducts.servicetracker.data.entities.User
import com.staxxproducts.servicetracker.data.entities.Vehicle

import com.staxxproducts.servicetracker.db

class UserViewModel(var application: Application): ViewModel() {

    private var vehicles: LiveData<List<Vehicle>>? = null

    internal val getAllUsers: LiveData<List<User>> = db.vehicleDao().getAllUsers()

    fun insertUser(user:List<User>){
         db.vehicleDao().insertUser(user)
    }

    suspend fun getUser(user: Long){
        db.vehicleDao().getUser(user)
    }
    fun deleteUserById(user: User) {
        db.vehicleDao().deleteUserById(user)
    }

}

