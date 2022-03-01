package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class AddRecordOLD: MainActivity() {

    //Declare variables
    private var vYr: String = ""
    private var vMk: String = ""
    private var vMd: String = ""
    private var vId: Int = 0
    private var svcDate: String = ""
    private var svcMile: String = ""
    private var svcType: String = ""
    private var svcNotes: String = ""
    private var listLength: Int = 0
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<ServiceOLD>? = null

    private var recLength: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)

        // Runs loadData to populate the list of services
      //  loadData()

        // Initializes Button and TextView
        val svcAddBtn = findViewById<Button>(R.id.addSvcBtn)
        val vehInfo = findViewById<TextView>(R.id.vehicleInfoTv)

        // Receive passed data from "Existing Vehicle" through onClickListener - CarID is the position of the clicked item
        vId = intent.getIntExtra("CarID", 0)
        vYr = intent.getStringExtra("Year").toString()
        vMk = intent.getStringExtra("Make").toString()
        vMd = intent.getStringExtra("Model").toString()
        listLength = intent.getIntExtra("ServiceLength", 0)

        // Returns the number of service records for vehicleID (vId)
      //  checkRecordLength(vId)

        // Sets the info TextView to year make and model for the vehicle
        val finalStr: String = ("$vYr $vMk $vMd")
        vehInfo.text = finalStr

        // Runs processes to add a new record when Add Button is clicked then sends user back to list of vehicles when done
        svcAddBtn.setOnClickListener {
           // addRecord()

            val intent = Intent(this, ExistingVehicle::class.java)
            intent.putExtra("ServiceID", listLength)
            startActivity(intent)
        }
    }
}


/*
    private fun addRecord() {

        // Prepare the input fields to receive data from the user
        val svcDateP = findViewById<DatePicker>(R.id.svcDateP)
        val mileageEt = findViewById<EditText>(R.id.svcMileEt)
        val svcTypeEt = findViewById<EditText>(R.id.svcTypeEt)
        val svcNotesEt = findViewById<EditText>(R.id.svcNotes)
        //Concatenate the various output strings from the DatePicker into one longer string and assign the input data to variables
        svcDate =
            (svcDateP.month.toString() + "." + svcDateP.dayOfMonth.toString() + "." + svcDateP.year.toString())
        svcMile = mileageEt.text.toString()
        svcType = svcTypeEt.text.toString()
        svcNotes = svcNotesEt.text.toString()

        // If the list of services is empty it sets the first record rather than adds to the list
        if (mServiceList!!.isEmpty()) {
            mServiceList!![recLength-1] = ServiceOLD(svcDate, svcMile, svcNotes, svcType)
        }
        else {
            mServiceList!!.add(recLength, ServiceOLD(svcDate, svcMile, svcNotes, svcType))
        }

        // mServiceList!!.add(recLength-1,Service(svcDate, svcMile, svcNotes, svcType))
        insertItem(vYr,vMk,vMd, mServiceList!!)

        // Calls up a fresh instance of shared preferences and adds the record to it
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mVehicleList1)
        editor.putString("vehicle list", json)
        editor.apply()
    }



    // Inserts a new service item to the vehicle list at position vId
     private fun insertItem(Year: String, Make: String, Model: String, mServiceList: ArrayList<ServiceOLD>) {
         mVehicleList1!![vId] = (VehicleServiceItem(Year,Make,Model,mServiceList))
    }

    // Populates the list of vehicles and their services for display
    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(vId).getJSONArray("Services").toString()
        val gson = Gson()

        val type: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type
        val type1: Type = object : TypeToken<ArrayList<ServiceOLD?>?>() {}.type


        mServiceList = gson.fromJson(jsonServices, type1)
        mVehicleList1 = gson.fromJson(json, type)

        if (mVehicleList1 == null) {
            mVehicleList1 = ArrayList()
        }

        if (mServiceList == null) {
            mServiceList = ArrayList()
        }
    }

    private fun checkRecordLength(position: Int) {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(position).getJSONArray("Services")
        recLength = jsonServices.length()


    }*/
