package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.vehicleservicetracker.data.*
import kotlin.concurrent.thread

class ViewServices: AppCompatActivity() {
/*    // Initializes Variables
    var finalStr: String = ""
    private var svcId: Int = 0
    private var vId: Int = 0
    private var mVehicleList1: ArrayList<VehicleServiceItem>? = null
    private var mServiceList: ArrayList<ServiceOLD>? = null
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: ServiceAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null*/

    private lateinit var model: ServiceViewModel
    lateinit var recyclerViewSvc: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_services)
    }

    override fun onResume() {
        super.onResume()
        val recyclerTV = findViewById<TextView>(R.id.serviceItemTextView)

        recyclerViewSvc = findViewById(R.id.svcNotesRv)

        //

        //   recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //   recyclerView.adapter = adapter


/*        val vehicleItem: String = ""
        adapter.list.add(vehicleItem)
        adapter.notifyDataSetChanged()*/
        var modelFactory = ServiceViewModelFactory(application)
        model = ViewModelProvider(this,modelFactory).get(ServiceViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        recyclerViewSvc.layoutManager = linearLayoutManager

        // val viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
/*        viewModel.getAllVehicles().observe(this) { it ->
            if (it != null) {
                adapter.list.clear()
                adapter.list.addAll(it.map { it.vehicleYear})
                adapter.notifyDataSetChanged()
            }
        }*/

        model.getAllVehicleServices.observe(this, Observer {  service ->
            recyclerViewSvc.adapter = ServiceAdapter(service)

            val swipeToDeleteCallback = object: SwipeToDeleteCallback(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val item = service[position]
                    (service as MutableList).remove(item)

                    thread {
                        db.vehicleDao().deleteByServiceId(item)

                    }
                    recyclerViewSvc.adapter?.notifyItemRemoved(position)

                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)

            itemTouchHelper.attachToRecyclerView(recyclerViewSvc)

            recyclerViewSvc.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {

                override fun onItemClick(view: View, position: Int) {
                    serviceId = position.toLong()
                    //  Toast.makeText(this@ViewServices,"You clicked $position",Toast.LENGTH_SHORT).show()

                    // Gets the position of the service item clicked and populates the TextView with that items notes
                    val intent1 = Intent(this@ViewServices, SingleService::class.java)
                    startActivity(intent1)

                }



            }))


        })

        // Initializes back button to send user back to Existing Vehicle list
        val backButton = findViewById<Button>(R.id.svcRecGoBackBtn)
        backButton.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)
            startActivity(intent)
        }
        // Initializes add button to send vehicle ID, year, make, and model to the AddRecord screen
        val addRecordBtn = findViewById<Button>(R.id.addNewRecBtn)
        addRecordBtn.setOnClickListener {
            val intent = Intent(this, AddRecord::class.java)
            startActivity(intent)
        }
    }

}
/*

        // Sets up the notes section for services
        val notesText = findViewById<TextView>(R.id.svcNotesTv)

        // Collects the serviceID number, the vehicle ID number, year, make, and model from previous screen's intent's passing
        svcId = intent.getIntExtra("ServiceID",0)
        vId = intent.getIntExtra("CarID",0)
        val vYr = intent.getStringExtra("Year").toString()
        val vMk = intent.getStringExtra("Make").toString()
        val vMd = intent.getStringExtra("Model").toString()


        // Runs loadData to populate the list of services
        loadData()

        // Populates the recycler view with the list of services
        buildRecyclerView()

        // Initializes back button to send user back to Existing Vehicle list
        val backButton = findViewById<Button>(R.id.svcRecGoBackBtn)
        backButton.setOnClickListener {
            val intent = Intent(this, ExistingVehicle::class.java)
            startActivity(intent)
        }

        // Initializes add button to send vehicle ID, year, make, and model to the AddRecord screen
        val addRecordBtn = findViewById<Button>(R.id.addNewRecBtn)
        addRecordBtn.setOnClickListener {
            val intent = Intent(this, AddRecord::class.java)

            // Passes vehicle data to next screen
            intent.putExtra("CarID",vId)
            intent.putExtra("Year",vYr)
            intent.putExtra("Make",vMk)
            intent.putExtra("Model",vMd)
            startActivity(intent)
        }

        // Sets the touch listener for the recycler view of services to send vehicle notes to the TextView
        mRecyclerView?.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
              //  Toast.makeText(this@ViewServices,"You clicked $position",Toast.LENGTH_SHORT).show()

                // Gets the position of the service item clicked and populates the TextView with that items notes
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

        val vehicleInfo: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type
        val serviceInfo: Type = object : TypeToken<ArrayList<ServiceOLD?>?>() {}.type


        mServiceList = gson.fromJson(jsonServices, serviceInfo)
        mVehicleList1 = gson.fromJson(json, vehicleInfo)
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


        val notesSvcEntry: String = jsonServices.getJSONObject(it).getString("serviceNotes")
        finalStr = (notesSvcEntry)

    }

*/

