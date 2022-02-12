package com.staxxproducts.vehicleservicetracker

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button


class AdminActivities: MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_screen)

        // Initializes buttons
        val backButton = findViewById<Button>(R.id.goBackBtn)
        val eraseList = findViewById<Button>(R.id.eraseListBtn)

        // Sends user back to the main screen
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
             }
        // Executes sureOrNot which asks user if they're sure they want to delete the entire list
        eraseList.setOnClickListener {
            sureOrNotEntire()
         }



    }


    // Creates a dialog box giving the user the option to proceed with list deletion or to back out to the main screen
    private fun sureOrNotEntire() {

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.are_you_sure_popup, null)
        val yesImSure  = dialogLayout.findViewById<Button>(R.id.yesSureBtn)
        val goBack  = dialogLayout.findViewById<Button>(R.id.sureGoBack)
        builder.setView(dialogLayout)
        goBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        yesImSure.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)
            val preferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
            preferences.edit().clear().apply()
            startActivity(intent)
        }

        builder.show()


    }
}