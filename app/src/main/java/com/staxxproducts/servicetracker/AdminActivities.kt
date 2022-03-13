package com.staxxproducts.servicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.staxxproducts.servicetracker.importexport.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DEFAULT_EXPORT_TITLE = "Vehicle_db"


class AdminActivities: MainActivity() {

    private val openDoc =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            vm.import(uri)
        }

    private val createDoc =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
            vm.export(uri)
        }

    private val vm: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_screen)

        // Initializes buttons
        val backButton = findViewById<Button>(R.id.goBackBtn)
        val backupButton = findViewById<Button>(R.id.backupBtn)
        val restoreButton = findViewById<Button>(R.id.restoreBtn)

        // Sends user back to the main screen
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
             }

        backupButton.setOnClickListener {
            createDoc.launch(DEFAULT_EXPORT_TITLE)

              }

        restoreButton.setOnClickListener {

            openDoc.launch(arrayOf("application/octet-stream"))

         }





    }


}