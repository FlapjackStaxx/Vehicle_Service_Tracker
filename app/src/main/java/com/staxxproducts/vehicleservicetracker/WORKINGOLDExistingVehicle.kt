package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type

/*
class  WORKINGOLDExistingVehicle: AppCompatActivity() {
    var finalStr: String = ""
    var passYr: String = ""
    var passMk: String = ""
    var passMd: String = ""
    var passId: Int = 0
    var mVehicleList: ArrayList<VehicleItem>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: VehicleAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_vehicle)
        loadData()
        buildRecyclerView()

        val backButton = findViewById<Button>(R.id.existGoBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }

             mRecyclerView?.addOnItemTouchListener(RecyclerItemClickListenr(this, mRecyclerView!!, object : RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@WORKINGOLDExistingVehicle,"You clicked $position",Toast.LENGTH_SHORT).show()
                getLine(position)
                Log.i(TAG, "getLine: $finalStr")

                val intent1 = Intent(this@WORKINGOLDExistingVehicle, AddRecord::class.java)
                passId = position
                intent1.putExtra("CarID",passId)
                intent1.putExtra("Year",passYr)
                intent1.putExtra("Make",passMk)
                intent1.putExtra("Model",passMd)
                startActivity(intent1)
            }

           // override fun onItemLongClick(view: View?, position: Int) {
        //        TODO("do nothing")
       //     }
        }))

    }








    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val type: Type = object : TypeToken<ArrayList<VehicleItem?>?>() {}.type
        mVehicleList = gson.fromJson(json, type)
        if (mVehicleList == null) {
            mVehicleList = ArrayList()
        }
    }

    private fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = VehicleAdapter(mVehicleList!!)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter
    }

    //Returns a string of the specific TextView clicked inside of the RecyclerView linked to the vehicle list

    fun getLine(it: Int){
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonObject = jsonArray.getJSONObject(it)

        val yearStr: String = jsonObject.getString("year")
        val makeStr: String = jsonObject.getString("make")
        val modelStr: String = jsonObject.getString("model")

        passYr = yearStr
        passMk = makeStr
        passMd = modelStr
        finalStr = ("$yearStr $makeStr $modelStr")

    }
}
*/