package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ExistingVehicle: AppCompatActivity() {


    //Declare variables
    private lateinit var adapter: ArrayAdapter<String>
    private var list: ArrayList<String> = ArrayList()
    private var start:Int = 0
    private val prefName: String = "VEHICLE_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existing_vehicle)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        //Declare variables
        val addButton = findViewById<Button>(R.id.addButton)
        val sharedPref = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val listLv = findViewById<ListView>(R.id.listExistingLv)
        val editor = sharedPref.edit()
        val counter = sharedPref.getInt("COUNT",0)

        adapter =ArrayAdapter(this, android.R.layout.simple_list_item_1, list)




        while(start < counter)
        {
            var dataItem=sharedPref.getString("YEAR$start", null).toString()
            dataItem += " "+ sharedPref.getString("MAKE$start", null).toString()
            dataItem +=" "+ sharedPref.getString("MODEL$start", null).toString()

            list.add(dataItem)
            adapter.notifyDataSetChanged()

            start += 1

        }

        if(list.size<1){
            list.add("Nothing Found")
        }


        listLv.adapter = adapter


        listLv.setOnClickListener {

            val yearEt = findViewById<EditText>(R.id.yearEt)
            val makeEt = findViewById<EditText>(R.id.makeEt)
            val modelEt = findViewById<EditText>(R.id.modelEt)

            val year: String = yearEt.text.toString()
            val make: String = makeEt.text.toString()
            val model: String = modelEt.text.toString()

            val counter: Int = sharedPref.getInt("COUNT", 0)
            editor.putInt("COUNT", counter + 1)
            editor.putString("YEAR$counter", year)
            editor.putString("MAKE$counter", make)
            editor.putString("MODEL$counter", model)

            editor.apply()
            editor.commit()
            loadPreferences()

        }




        //Reloads the ListView element
        //Only way to make notifyDataSetChanged work with the ArrayList
    }
    private fun loadPreferences(){
        val data = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        var dataSet = data.getString("YEAR$start", "None Available")
        dataSet += " "+ data.getString("MAKE$start", null)
        dataSet += " "+ data.getString("MODEL$start", null)

        adapter.add(dataSet)
        adapter.notifyDataSetChanged()
    }

}