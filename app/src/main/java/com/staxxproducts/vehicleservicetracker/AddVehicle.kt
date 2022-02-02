package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class AddVehicle: AppCompatActivity() {


    // Declare Global Variables
    private var listYear: MutableList<String> = ArrayList()
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<Service>? = null
    private val fromYear: String = "2022"
    private val toYear: String = "1900"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)


        loadData()


        // Declare Local Variables
        val yearSpin = findViewById<Spinner>(R.id.yearSpin)
        val adapterSpin = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listYear)
        val buttonSave = findViewById<Button>(R.id.addButton)

        //Get range of years for spinner
        listYear = getYearRange(fromYear, toYear)
        yearSpin.adapter = adapterSpin

        //Executes saveData to add vehicle inputs to Gson
        buttonSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val year = findViewById<Spinner>(R.id.yearSpin)
        val make = findViewById<EditText>(R.id.makeEt)
        val model = findViewById<EditText>(R.id.modelEt)

        val date = ""
        val mileage = ""
        val servicenotes = ""
        val typeofservice = ""
        mServiceList!!.add(Service(date,mileage,servicenotes,typeofservice))

        insertItem(year.selectedItem.toString(), make.text.toString(), model.text.toString(), mServiceList!!)

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mVehicleList1)
        editor.putString("vehicle list", json)
        editor.apply()
        val intent = Intent(this, ExistingVehicle::class.java)
        startActivity(intent)
    }


    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val type: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type

        mVehicleList1 = gson.fromJson(json, type)
        if (mVehicleList1 == null) {
            mVehicleList1 = ArrayList()
        }

        if (mServiceList == null) {
            mServiceList = ArrayList()
        }
    }



    private fun insertItem(Year: String, Make: String, Model: String, mServiceList: ArrayList<Service>) {
        mVehicleList1!!.add(VehicleServiceItem(Year,Make,Model,mServiceList))
    }

    private fun getYearRange(startYear: String, endYear: String): MutableList<String> {
        var cur = startYear.toInt()
        val stop = endYear.toInt()
        while (cur >= stop) {
            listYear.add(cur--.toString())
        }
        return listYear
    }
}