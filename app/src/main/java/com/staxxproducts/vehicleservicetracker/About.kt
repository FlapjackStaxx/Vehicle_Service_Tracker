package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class About: MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_screen)

    val backButton = findViewById<Button>(R.id.goBackBtn)

    backButton.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
         }
    }
}