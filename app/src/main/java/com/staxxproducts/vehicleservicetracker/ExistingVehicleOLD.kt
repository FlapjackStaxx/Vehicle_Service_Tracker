/*
package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type


class  ExistingVehicleOLD: AppCompatActivity() {
    var finalStr: String = ""
    var passYr: String = ""
    var passMk: String = ""
    var passMd: String = ""
    var passId: Int = 0
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<ServiceOLD>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: VehicleAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var recLength: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_vehicle)

        // Runs loadData to populate the list of services
        loadData()
        // Populates the recycler view with the list of vehicles
        buildRecyclerView()

        // Sends user back to the main screen
        val backButton = findViewById<Button>(R.id.existGoBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        // Sets the touch listener for the recycler view of vehicles
        mRecyclerView?.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {

        override fun onItemClick(view: View, position: Int) {
           // Toast.makeText(this@ExistingVehicle,"You clicked $position",Toast.LENGTH_SHORT).show()
            getLine(position)
            checkRecordLength(position)


            val intent1 = Intent(this@ExistingVehicleOLD, ViewServices::class.java)
            passId = position
            intent1.putExtra("ServiceLength",recLength)
            intent1.putExtra("CarID",passId)
            intent1.putExtra("Year",passYr)
            intent1.putExtra("Make",passMk)
            intent1.putExtra("Model",passMd)
            startActivity(intent1)
        }

     }))

    }




    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val vehicleServices: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type

        mVehicleList1 = gson.fromJson(json, vehicleServices)
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

    // Returns a string of the specific TextView clicked inside of the RecyclerView linked to the vehicle list
    fun getLine(it: Int){
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonObject = jsonArray.getJSONObject(it)

        val yearStr: String = jsonObject.getString("Year")
        val makeStr: String = jsonObject.getString("Make")
        val modelStr: String = jsonObject.getString("Model")

        passYr = yearStr
        passMk = makeStr
        passMd = modelStr
        finalStr = ("$yearStr $makeStr $modelStr")

    }


    // Returns the number of service records
    private fun checkRecordLength(position: Int) {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(position).getJSONArray("Services")
        recLength = jsonServices.length()
    }


}*/
