package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.*


class AddRecord: MainActivity() {

    //Declare variables
    private lateinit var adapter: ArrayAdapter<String>
    private var list: ArrayList<String> = ArrayList()
    private var start:Int = 0
    private val prefName: String = "VEHICLE_LIST"
    var vEntryNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)
       // window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val mIntent = intent
        vEntryNumber = mIntent.getIntExtra("VEH_NUM", 0)

        //Declare variables
        val addButton = findViewById<Button>(R.id.addButton)
        val sharedPref = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var vehicleInfo = findViewById<TextView>(R.id.vehicleInfoTv)

       // adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)


        var dataItem=sharedPref.getString("YEAR$vEntryNumber", null).toString()
        dataItem += " "+ sharedPref.getString("MAKE$vEntryNumber", null).toString()
        dataItem +=" "+ sharedPref.getString("MODEL$vEntryNumber", null).toString()
        vehicleInfo.text = dataItem




//        addButton.setOnClickListener {


     //   }




        //Reloads the ListView element
        //Only way to make notifyDataSetChanged work with the ArrayList
    }


}