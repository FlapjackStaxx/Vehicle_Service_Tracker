package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class AddRecord: MainActivity() {

    //Declare variables
    private var vYr: String = ""
    private var vMk: String = ""
    private var vMd: String = ""
    private var vId: Int = 0
    private val TAG: String = "Vehicle Info: "
    private var svcDate: String = ""
    private var svcMile: String = ""
    private var svcType: String = ""
    private var svcNotes: String = ""
    private var listLength: Int = 0
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<Service>? = null

    private var recLength: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)

        loadData()

         val TAG = "MyActivity"

        val svcAddBtn = findViewById<Button>(R.id.addSvcBtn)
        val vehInfo = findViewById<TextView>(R.id.vehicleInfoTv)

        // Receive passed data from "Existing Vehicle" through onClickListener - CarID is the position of the clicked item
        vId = intent.getIntExtra("CarID",0)
        vYr = intent.getStringExtra("Year").toString()
        vMk = intent.getStringExtra("Make").toString()
        vMd = intent.getStringExtra("Model").toString()
         listLength= intent.getIntExtra("ServiceLength",0)

        checkRecordLength(vId)

        val finalStr: String = ("$vYr $vMk $vMd")
        Log.i(TAG, " $finalStr")
        vehInfo.text = finalStr


        svcAddBtn.setOnClickListener {


            addRecord()
            val intent = Intent(this, ViewServices::class.java)

            intent.putExtra("ServiceID",listLength)

            startActivity(intent)
        }
    }




    private fun addRecord() {
/*
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(vId).getJSONArray("Services")
        val editor = sharedPreferences.edit()


        // Collect information from the parsed array. Data was in object inside of an array "Services" inside of an object which was inside of the master array.
        val dateEntry: String = jsonServices.getJSONObject(0).getString("date")
        val mileEntry: String = jsonServices.getJSONObject(0).getString("mileage")
        val typeSvcEntry: String = jsonServices.getJSONObject(0).getString("typeofservice")
        val notesSvcEntry: String = jsonServices.getJSONObject(0).getString("servicenotes")


*/

        // Prepare the input fields to receive data from the user
        val svcDateP = findViewById<DatePicker>(R.id.svcDateP)
        val mileageEt = findViewById<EditText>(R.id.svcMileEt)
        val svcTypeEt = findViewById<EditText>(R.id.svcTypeEt)
        val svcNotesEt = findViewById<EditText>(R.id.svcNotes)
        //Concatenate the various output strings from the DatePicker into one longer string and assign the input data to variables
        svcDate =
            (svcDateP.month.toString() + "-" + svcDateP.dayOfMonth.toString() + "-" + svcDateP.year.toString())
        svcMile = mileageEt.text.toString()
        svcType = svcTypeEt.text.toString()
        svcNotes = svcNotesEt.text.toString()
        if (mServiceList!!.isEmpty()) {
            mServiceList!!.set(recLength-1,Service(svcDate, svcMile, svcNotes, svcType))
        }
        else {
            mServiceList!!.add(recLength, Service(svcDate, svcMile, svcNotes, svcType))
        }

       // mServiceList!!.add(recLength-1,Service(svcDate, svcMile, svcNotes, svcType))
        insertItem(vYr,vMk,vMd, mServiceList!!)

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mVehicleList1)
        editor.putString("vehicle list", json)
        editor.apply()
    }
/*

            // Removed the key:value pairs from the "Services" array to prepare for new form data
           jsonServices.getJSONObject(svcEntryNum).remove("date")
            jsonServices.getJSONObject(svcEntryNum).remove("mileage")
            jsonServices.getJSONObject(svcEntryNum).remove("typeofservice")
            jsonServices.getJSONObject(svcEntryNum).remove("servicenotes")

        // Assign new key:value pairs from the form data
        jsonServices.getJSONObject(svcEntryNum).putOpt("date",svcDate)
        jsonServices.getJSONObject(svcEntryNum).putOpt("mileage",svcMile)
        jsonServices.getJSONObject(svcEntryNum).putOpt("typeofservice",svcType)
        jsonServices.getJSONObject(svcEntryNum).putOpt("servicenotes",svcNotes)

        //Create new output string and send it off to the sharedpreferences file
        val outNew: String = jsonArray.toString()
        editor.clear()
        editor.putString("vehicle list", outNew)
        editor.apply()
*/




        /*
        // General output logging for debugging purposes
        Log.i("Old Info: ", "Date: $dateEntry Mileage: $mileEntry Service Type: $typeSvcEntry Service Notes: $svcNotesEntry")
        Log.i("New Info: ", "Date: $svcDate Mileage: $svcMile Service Type: $svcType Service Notes: $svcNotes")
        */





     private fun insertItem(Year: String, Make: String, Model: String, mServiceList: ArrayList<Service>) {
         mVehicleList1!![vId] = (VehicleServiceItem(Year,Make,Model,mServiceList))
    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(vId).getJSONArray("Services").toString()

        val gson = Gson()

        val type: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type
        val type1: Type = object : TypeToken<ArrayList<Service?>?>() {}.type


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
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(position).getJSONArray("Services")
        recLength = jsonServices.length()


        Log.i("Service Record # ",recLength.toString())

    }
}