package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addVehButton)
        val existButton = findViewById<Button>(R.id.existingButton)
        val aboutButton = findViewById<Button>(R.id.abtButton)

        addButton.setOnClickListener {
            val intent = Intent(this, AddVehicle::class.java)
            startActivity(intent)
        }

        existButton.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)
            startActivity(intent)
        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }



    }
}