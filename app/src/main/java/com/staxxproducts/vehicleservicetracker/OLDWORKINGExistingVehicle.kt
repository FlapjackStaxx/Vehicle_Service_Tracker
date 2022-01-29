package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class OLDWORKINGExistingVehicle: AppCompatActivity() {


    //Declare variables
    private lateinit var adapter: ArrayAdapter<String>
    private var list: ArrayList<String> = ArrayList()
    private var start:Int = 0
    private val prefName: String = "VEHICLE_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_vehicle)


    }


    private fun loadPreferences(){
        val data = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        var dataSet = data.getString("YEAR$start", "None Available")
        dataSet += " "+ data.getString("MAKE$start", null)
        dataSet += " "+ data.getString("MODEL$start", null)

        adapter.add(dataSet)
        adapter.notifyDataSetChanged()
    }

}