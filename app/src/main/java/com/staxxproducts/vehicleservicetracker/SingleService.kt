package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.staxxproducts.vehicleservicetracker.data.ServiceViewModel
import com.staxxproducts.vehicleservicetracker.data.VehicleDatabase
import kotlin.concurrent.thread


private lateinit var model: ServiceViewModel

class SingleService: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicle_service)
        var serviceDateTv = findViewById<TextView>(R.id.svcDate1Tv)
        var serviceMileageTv = findViewById<TextView>(R.id.svcMileage1Tv)
        var serviceTypeTv = findViewById<TextView>(R.id.svcType1Tv)
        var serviceNotesTv = findViewById<TextView>(R.id.svcNotes1Tv)
        val backButton = findViewById<Button>(R.id.goBackViewSvcBtn)

        serviceDateTv.text = serviceDate
        serviceMileageTv.text = serviceMileage
        serviceTypeTv.text = serviceType
        serviceNotesTv.text = serviceNotes

        backButton.setOnClickListener {
            val intent = Intent(this, ViewServices::class.java)
            startActivity(intent)
        }



}
    }



