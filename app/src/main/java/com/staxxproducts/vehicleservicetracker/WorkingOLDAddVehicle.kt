package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class WorkingOLDAddVehicle: AppCompatActivity() {


    //Declare variables
    private lateinit var adapter: ArrayAdapter<String>
    private var start:Int = 0
    private val prefName: String = "VEHICLE_LIST"
    private val fromYear: String = "2022"
    private val toYear: String = "1900"
    private var listYear: MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)

        //Declare variables
        val addButton = findViewById<Button>(R.id.addButton)
        val sharedPref = getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val yearSpin = findViewById<Spinner>(R.id.yearSpin)
        val makeEt = findViewById<EditText>(R.id.makeEt)
        val modelEt = findViewById<EditText>(R.id.modelEt)
        var make: String = "No Make Entered"
        var model: String = "No Model Entered"
        val vehInfo: MutableList<Vehicle>

        listYear = getYearRange(fromYear, toYear)


       // adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        val adapterSpin = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listYear)
        yearSpin.adapter = adapterSpin




        addButton.setOnClickListener {
            val year: String = yearSpin.selectedItem.toString()
            make = makeEt.text.toString()
            model = modelEt.text.toString()

            var vehCount: Int = sharedPref.getInt("COUNT", 0)

            editor.putInt("COUNT", vehCount)
            editor.putString("YEAR", year)
            editor.putString("MAKE", make)
            editor.putString("MODEL", model)

            editor.apply()
            editor.commit()


          //  loadPreferences()


            Toast.makeText(this,"You clicked on $year $make $model",Toast.LENGTH_LONG).show()
            vehCount++

        }

    }





    private fun getYearRange(startYear: String, endYear: String): MutableList<String> {
        var cur = startYear.toInt()
        val stop = endYear.toInt()
        while (cur >= stop) {
            listYear.add(cur--.toString())
        }
        return listYear
    }








    //Reloads the ListView element
    //Only way to make notifyDataSetChanged work with the ArrayList

  /*  private fun loadPreferences(){
        val data = getSharedPreferences(prefName,Context.MODE_PRIVATE)
        var dataSet = data.getString("YEAR$start", "None Available")
        dataSet += " "+ data.getString("MAKE$start", null)
        dataSet += " "+ data.getString("MODEL$start", null)

        adapter.add(dataSet)
        adapter.notifyDataSetChanged()
    }*/


}
