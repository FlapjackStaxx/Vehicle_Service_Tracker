package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity




class SingleService: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vehicle_service)
        val serviceDateTv = findViewById<TextView>(R.id.svcDate1Tv)
        val serviceMileageTv = findViewById<TextView>(R.id.svcMileage1Tv)
        val serviceTypeTv = findViewById<TextView>(R.id.svcType1Tv)
        val serviceNotesTv = findViewById<TextView>(R.id.svcNotes1Tv)
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



