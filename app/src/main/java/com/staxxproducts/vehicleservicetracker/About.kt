package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class About: MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_screen)


        // Initialize buttons
        val backButton = findViewById<Button>(R.id.goBackBtn)
        val adminButton = findViewById<Button>(R.id.adminBtn)

        // Returns user to first screen
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Sends user to Admin Activities
        adminButton.setOnClickListener {
            val intent = Intent(this, AdminActivities::class.java)
            startActivity(intent)
        }
    }
}