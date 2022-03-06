package com.staxxproducts.vehicleservicetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        model = ViewModelProvider(this, modelFactory)[VehicleViewModel::class.java]
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

        model.getAllVehicles.observe(this) { vehicle ->
            recyclerView.adapter = VehicleAdapter(vehicle)

            val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
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

        }

        // Sends user back to the main screen
        val backButton = findViewById<Button>(R.id.existGoBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }
}

