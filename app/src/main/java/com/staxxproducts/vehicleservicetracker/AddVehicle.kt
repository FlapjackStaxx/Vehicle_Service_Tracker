package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddVehicle: AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences



    private lateinit var adapter1: ArrayAdapter<*>

    val list = ArrayList<String>()
    var start:Int = 0
    var counter: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)


        val addButton = findViewById<Button>(R.id.addButton)

        sharedPreferences = getSharedPreferences("VEHICLE_LIST", Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt("VKEY", 0)




        addButton.setOnClickListener {


            addItems()


        }
    }

     private fun populateList() {

        val preferences = getSharedPreferences("VEHICLE_LIST", Context.MODE_PRIVATE)
      //  val allStrings: String? = preferences.getString("YEAR","MAKE","MODEL")
        val year:  String? = preferences.getString("YEAR", null)
        val make:  String? = preferences.getString("MAKE", null)
        val model: String? = preferences.getString("MODEL", null)

       // val vehicles = arrayOf(year make model)
          val mListview = findViewById<ListView>(R.id.listLv)

        adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

             while(start < counter)
             {
                list.add("$year, $make, $model")
                start=start+1
         }

         if(list.size<1){
             list.add("Nothing Found")
         }
        mListview.adapter = adapter1



     }

     private fun addItems(){
         //val count: Int = sharedPreferences.getInt("VKEY", 0)

         val yearEt = findViewById<EditText>(R.id.yearEt)
         val makeEt = findViewById<EditText>(R.id.makeEt)
         val modelEt = findViewById<EditText>(R.id.modelEt)

         val year: String = yearEt.text.toString()
         val make: String = makeEt.text.toString()
         val model: String = modelEt.text.toString()

         val editor: SharedPreferences.Editor = sharedPreferences.edit()
         editor.putInt("VKEY",counter+1)
         editor.putString("YEAR", year)
         editor.putString("MAKE", make)
         editor.putString("MODEL", model)
         editor.apply()

         Toast.makeText(this, "Saved vehicle information!", Toast.LENGTH_SHORT).show()

         populateList()



        }



    }
