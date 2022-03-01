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


class  ExistingVehicle: AppCompatActivity() {

    //val dao = VehicleDatabase.getInstance(applicationContext).vehicleDao
    private lateinit var model: VehicleViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_vehicle)
    }

    override fun onResume() {
        super.onResume()


        val recyclerTV = findViewById<TextView>(R.id.vehicleItemTv)
        recyclerView = findViewById(R.id.recyclerview)
        //

        //   recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


/*        val vehicleItem: String = ""
        adapter.list.add(vehicleItem)
        adapter.notifyDataSetChanged()*/
        val modelFactory = VehicleViewModelFactory(application)
        model = ViewModelProvider(this, modelFactory).get(VehicleViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        recyclerView.layoutManager = linearLayoutManager

        // val viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
/*        viewModel.getAllVehicles().observe(this) { it ->
            if (it != null) {
                adapter.list.clear()
                adapter.list.addAll(it.map { it.vehicleYear})
                adapter.notifyDataSetChanged()
            }
        }*/

        model.getAllVehicles.observe(this, Observer { vehicle ->
            recyclerView.adapter = VehicleAdapter(vehicle)

            val swipeToDeleteCallback = object: SwipeToDeleteCallback(){
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val item = vehicle[position]
                    (vehicle as MutableList).remove(item)

                    thread {
                        db.vehicleDao().deleteVehicleById(item)

                    }
                    recyclerView.adapter?.notifyItemRemoved(position)

                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)

            itemTouchHelper.attachToRecyclerView(recyclerView)
            recyclerView.addOnItemTouchListener(
                RecyclerItemClickListener(
                    this,
                    object : RecyclerItemClickListener.OnItemClickListener {

                        override fun onItemClick(view: View, position: Int) {
                            //  Toast.makeText(this@ViewServices,"You clicked $position",Toast.LENGTH_SHORT).show()

                            // Gets the position of the service item clicked and populates the TextView with that items notes
                            vehicleId = position.toLong() + 1
                            val intent1 = Intent(this@ExistingVehicle, ViewServices::class.java)


                            startActivity(intent1)
                        }


                    })
            )

        })

        // Sends user back to the main screen
        val backButton = findViewById<Button>(R.id.existGoBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }
}






















/*

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


            val intent1 = Intent(this@ExistingVehicle, ViewServices::class.java)
            passId = position
            intent1.putExtra("CarID",passId)
*/
/*            intent1.putExtra("Year",passYr)
            intent1.putExtra("Make",passMk)
             intent1.putExtra("ServiceLength",recLength)

            intent1.putExtra("Model",passMd)
            startActivity(intent1)*//*

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
