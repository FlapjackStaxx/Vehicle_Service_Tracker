package com.staxxproducts.vehicleservicetracker

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import org.json.JSONArray


class AddRecord: MainActivity() {

    //Declare variables
    private var vYr: String = ""
    private var vMk: String = ""
    private var vMd: String = ""
    private var vId: Int = 0
    private val TAG: String = "Vehicle Info: "
    var svcDate: String = ""
    var svcMile: String = ""
    var svcType: String = ""
    var svcNotes: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)

        val svcAddBtn = findViewById<Button>(R.id.addSvcBtn)
        val vehInfo = findViewById<TextView>(R.id.vehicleInfoTv)

        // Receive passed data from "Existing Vehicle" through onClickListener - CarID is the position of the clicked item
        vId = intent.getIntExtra("CarID",0)
        vYr = intent.getStringExtra("Year").toString()
        vMk = intent.getStringExtra("Make").toString()
        vMd = intent.getStringExtra("Model").toString()

        val finalStr: String = ("$vYr $vMk $vMd")
        Log.i(TAG, " $finalStr")
        vehInfo.text = finalStr


        svcAddBtn.setOnClickListener{ addRecord(0) }
    }






    private fun addRecord(svcEntryNum: Int) {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(0).getJSONArray("Services")
        val editor = sharedPreferences.edit()


        // Collect information from the parsed array. Data was in object inside of an array "Services" inside of an object which was inside of the master array.
        val dateEntry: String = jsonServices.getJSONObject(0).getString("date")
        val mileEntry: String = jsonServices.getJSONObject(0).getString("mileage")
        val typeSvcEntry: String = jsonServices.getJSONObject(0).getString("typeofservice")



        // Prepare the input fields to receive data from the user
        val svcDateP = findViewById<DatePicker>(R.id.svcDateP)
        val mileageEt = findViewById<EditText>(R.id.svcMileEt)
        val svcTypeEt = findViewById<EditText>(R.id.svcTypeEt)
        val svcNotesEt = findViewById<EditText>(R.id.svcNotes)
        //Concatenate the various output strings from the DatePicker into one longer string and assign the input data to variables
        svcDate = (svcDateP.month.toString() + "-" + svcDateP.dayOfMonth.toString() + "-" + svcDateP.year.toString())
        svcMile = mileageEt.text.toString()
        svcType = svcTypeEt.text.toString()
        svcNotes = svcNotesEt.text.toString()

        // Removed the key:value pairs from the "Services" array to prepare for new form data
        jsonServices.getJSONObject(0).remove("date")
        jsonServices.getJSONObject(0).remove("mileage")
        jsonServices.getJSONObject(0).remove("typeofservice")
        jsonServices.getJSONObject(0).remove("servicenotes")

        // Assign new key:value pairs from the form data
        jsonServices.getJSONObject(0).put("date",svcDate)
        jsonServices.getJSONObject(0).put("mileage",svcMile)
        jsonServices.getJSONObject(0).put("typeofservice",svcType)
        jsonServices.getJSONObject(0).put("servicenotes",svcNotes)

        //Create new output string and send it off to the sharedpreferences file
        val outNew: String = jsonArray.toString()
        editor.clear()
        editor.putString("vehicle list", outNew)
        editor.apply()

        /*
        // General output logging for debugging purposes
        Log.i("Old Info: ", "Date: $dateEntry Mileage: $mileEntry Service Type: $typeSvcEntry Service Notes: $svcNotesEntry")
        Log.i("New Info: ", "Date: $svcDate Mileage: $svcMile Service Type: $svcType Service Notes: $svcNotes")
        */


    }


}