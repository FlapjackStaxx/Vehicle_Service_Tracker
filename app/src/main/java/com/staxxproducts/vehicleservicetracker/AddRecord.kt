package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import org.w3c.dom.Text


class AddRecord: MainActivity() {

    //Declare variables
    private lateinit var adapter: ArrayAdapter<String>
    private var list: ArrayList<String> = ArrayList()
    private var start: Int = 0
    private val prefName: String = "VEHICLE_LIST"
    var vYr: String = ""
    var vMk: String = ""
    var vMd: String = ""
    var vId: Int = 0
    val TAG: String = "Vehicle Info: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_record)
        var vehInfo = findViewById<TextView>(R.id.vehicleInfoTv)
        vId = intent.getIntExtra("Id",0)
        vYr = intent.getStringExtra("Year").toString()
        vMk = intent.getStringExtra("Make").toString()
        vMd = intent.getStringExtra("Model").toString()
        val finalStr: String = ("$vYr $vMk $vMd")
        Log.i(TAG, " $finalStr")
        vehInfo.text = finalStr + vId.toString()



    }
}