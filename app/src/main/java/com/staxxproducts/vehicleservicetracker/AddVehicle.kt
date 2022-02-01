package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class AddVehicle: AppCompatActivity() {


    //Declare Global Variables

    private var listYear: MutableList<String> = ArrayList()
    private var mVehicleList: ArrayList<VehicleItem>? = null
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<Service>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: VehicleAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val fromYear: String = "2022"
    private val toYear: String = "1900"
    private val id: String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)


        loadData()
    //    buildRecyclerView()

        //Declare Local Variables

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
        val mileage = " "
        val servicenotes = " "
        val typeofservice = " "
        mServiceList!!.add(Service(date,mileage,servicenotes,typeofservice))

        insertItem(id,year.selectedItem.toString(), make.text.toString(), model.text.toString(), mServiceList!!)

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

    private fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = VehicleAdapter(mVehicleList1!!)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter
    }


    private fun insertItem(id: String, Year: String, Make: String, Model: String, mServiceList: ArrayList<Service>) {
        mVehicleList1!!.add(VehicleServiceItem(id,Make,Model,mServiceList, Year))
        //    mAdapter!!.notifyItemInserted(mVehicleList!!.size)
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