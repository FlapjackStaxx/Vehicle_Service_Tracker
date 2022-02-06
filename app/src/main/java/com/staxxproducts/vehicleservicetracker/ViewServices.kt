package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type

class ViewServices: AppCompatActivity() {

    var finalStr: String = ""
    var passYr: String = ""
    var passMk: String = ""
    var passMd: String = ""
    var svcId: Int = 0
    var vId: Int=0
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<Service>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: ServiceAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_services)


        var notesText = findViewById<TextView>(R.id.svcNotesTv)

        svcId = intent.getIntExtra("ServiceID",0)
       vId = intent.getIntExtra("CarID",0)
       var vYr = intent.getStringExtra("Year").toString()
       var vMk = intent.getStringExtra("Make").toString()
       var vMd = intent.getStringExtra("Model").toString()


        loadData()
        buildRecyclerView()

        val backButton = findViewById<Button>(R.id.svcRecGoBackBtn)
        backButton.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)

            startActivity(intent)
        }

        val addRecordBtn = findViewById<Button>(R.id.addNewRecBtn)
        addRecordBtn.setOnClickListener {
            val intent = Intent(this, AddRecord::class.java)
            intent.putExtra("CarID",vId)
            intent.putExtra("Year",vYr)
            intent.putExtra("Make",vMk)
            intent.putExtra("Model",vMd)
            startActivity(intent)
        }

        mRecyclerView?.addOnItemTouchListener(RecyclerItemClickListenr(this, mRecyclerView!!, object : RecyclerItemClickListenr.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@ViewServices,"You clicked $position",Toast.LENGTH_SHORT).show()
                getLine(position)
                notesText.text=finalStr



            }

        }))

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

    private fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.svcNotesRv)
        mRecyclerView?.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ServiceAdapter(mServiceList!!)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter
    }

    // Returns a string of the specific TextView clicked inside of the RecyclerView linked to the vehicle list
    fun getLine(it: Int){
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val json = sharedPreferences.getString("vehicle list", null)
        val jsonArray = JSONArray(json)
        val jsonServices = jsonArray.getJSONObject(vId).getJSONArray("Services")



        val notesSvcEntry: String = jsonServices.getJSONObject(it).getString("servicenotes")


        finalStr = ("$notesSvcEntry")

    }


}