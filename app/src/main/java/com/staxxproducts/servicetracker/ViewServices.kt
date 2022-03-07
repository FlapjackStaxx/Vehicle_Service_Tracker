package com.staxxproducts.servicetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.servicetracker.data.*
import com.staxxproducts.servicetracker.data.adapters.ServiceAdapter
import com.staxxproducts.servicetracker.data.viewmodels.ServiceViewModel
import com.staxxproducts.servicetracker.data.viewmodels.ServiceViewModelFactory
import servicetracker.R
import kotlin.concurrent.thread

class ViewServices: AppCompatActivity() {


    private lateinit var model: ServiceViewModel
    lateinit var recyclerViewSvc: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_services)
    }

    override fun onResume() {
        super.onResume()

        recyclerViewSvc = findViewById(R.id.svcNotesRv)


        val modelFactory = ServiceViewModelFactory(application)
        model = ViewModelProvider(this,modelFactory)[ServiceViewModel::class.java]
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        recyclerViewSvc.layoutManager = linearLayoutManager



        model.getAllVehicleServices.observe(this) { service ->
            recyclerViewSvc.adapter = ServiceAdapter(service)

            val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
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

            recyclerViewSvc.addOnItemTouchListener(
                RecyclerItemClickListener(
                    this,
                    object : RecyclerItemClickListener.OnItemClickListener {

                        override fun onItemClick(view: View, position: Int) {
                            serviceId = position.toLong()
                            //  Toast.makeText(this@ViewServices,"You clicked $position",Toast.LENGTH_SHORT).show()

                            // Gets the position of the service item clicked and populates the TextView with that items notes
                            val intent1 = Intent(this@ViewServices, SingleService::class.java)
                            startActivity(intent1)

                        }


                    })
            )


        }

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
