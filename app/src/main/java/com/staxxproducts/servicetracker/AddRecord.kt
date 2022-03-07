package com.staxxproducts.servicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.staxxproducts.servicetracker.data.Service
import kotlin.concurrent.thread


class AddRecord: MainActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)

        // Prepare the input fields to receive data from the user
        val svcDateP = findViewById<DatePicker>(R.id.svcDateP)
        val mileageEt = findViewById<EditText>(R.id.svcMileEt)
        val svcTypeEt = findViewById<EditText>(R.id.svcTypeEt)
        val svcNotesEt = findViewById<EditText>(R.id.svcNotes)

        val svcAddBtn = findViewById<Button>(R.id.addSvcBtn)


        //Concatenate the various output strings from the DatePicker into one longer string and assign the input data to variables


        svcAddBtn.setOnClickListener {

            val svcDate =
                (svcDateP.month.toString() + "-" + svcDateP.dayOfMonth.toString() + "-" + svcDateP.year.toString())
            val  svcMile = mileageEt.text.toString()
            val svcType = svcTypeEt.text.toString()
            val svcNotes = svcNotesEt.text.toString()

            val serviceAdd = listOf(
                Service(null, vehicleId,svcDate,svcMile,svcType,svcNotes))
            thread {
                db.vehicleDao().insertService(serviceAdd)
            }
            val intent = Intent(this, ExistingVehicle::class.java)
            startActivity(intent)
        }




    }
}
