package com.staxxproducts.servicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
/*import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory*/

var vehicleId: Long = 1
var serviceId: Long = 1
var serviceDate = ""
var serviceMileage = ""
var serviceType = ""
var serviceNotes = ""

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        // Enable Firebase AppCheck
        FirebaseApp.initializeApp(*//*context=*//*this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            SafetyNetAppCheckProviderFactory.getInstance()
        )*/


        // Initializes buttons
        val addButton = findViewById<Button>(R.id.addVehButton)
        val existButton = findViewById<Button>(R.id.existingButton)
        val aboutButton = findViewById<Button>(R.id.abtButton)

        // Adds a new vehicle on click
        addButton.setOnClickListener {
            val intent = Intent(this, AddVehicle::class.java)
            startActivity(intent)
        }

        // Sends user to Existing Vehicle list
        existButton.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)
            startActivity(intent)
        }

        // Sends user to About screen
        aboutButton.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }



    }
}