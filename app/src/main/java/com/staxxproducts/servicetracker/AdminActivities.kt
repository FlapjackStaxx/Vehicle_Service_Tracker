package com.staxxproducts.servicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import servicetracker.R


class AdminActivities: MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_screen)

        // Initializes buttons
        val backButton = findViewById<Button>(R.id.goBackBtn)

        // Sends user back to the main screen
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
             }



    }

}