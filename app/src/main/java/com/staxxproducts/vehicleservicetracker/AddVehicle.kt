package com.staxxproducts.vehicleservicetracker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddVehicle: AppCompatActivity() {
    val prefName: String = "VEHICLE_LIST"



    /*
        TO DO LIST!!!
    ---Clean Up Code!
     */

 //   private lateinit var sharedPreferences: SharedPreferences


 //   private lateinit var adapter1: ArrayAdapter<*>

    //val list = ArrayList<String>()
   // var start: Int = 0
   // var counter: Int = 0
    lateinit var adapter: ArrayAdapter<String>
     var list: ArrayList<String> = ArrayList()
    var start:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)

        var sharedPref = getSharedPreferences(prefName,Context.MODE_PRIVATE)
        var editor = sharedPref.edit()

        //list = ArrayList<String>()
        var counter = sharedPref.getInt("COUNT",0)

         adapter =ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        var listLv = findViewById<ListView>(R.id.listLv)


        val addButton = findViewById<Button>(R.id.addButton)

        while(start < counter)
        {
            var dataItem=sharedPref.getString("YEAR"+start, null).toString()
            dataItem += " "+ sharedPref.getString("MAKE"+start, null).toString()
            dataItem +=" "+ sharedPref.getString("MODEL"+start, null).toString()

            list.add(dataItem)
            adapter.notifyDataSetChanged()




            start += 1

        }

        if(list.size<1){
            list.add("Nothing Found")
        }


       listLv.adapter = adapter
        //adapter.notifyDataSetChanged()

       // var sharedPref = getSharedPreferences(prefName,Context.MODE_PRIVATE)
      //  var editor = sharedPref.edit()


    //    sharedPreferences = getSharedPreferences("VEHICLE_LIST", Context.MODE_PRIVATE)
       // counter = sharedPreferences.getInt("VKEY", 0)


        addButton.setOnClickListener {


            var yearEt = findViewById<EditText>(R.id.yearEt)
            var makeEt = findViewById<EditText>(R.id.makeEt)
            var modelEt = findViewById<EditText>(R.id.modelEt)

            var year: String = yearEt.text.toString()
            var make: String = makeEt.text.toString()
            var model: String = modelEt.text.toString()

            var counter: Int = sharedPref.getInt("COUNT", 0)
            editor.putInt("COUNT", counter + 1)
            editor.putString("YEAR"+counter, year)
            editor.putString("MAKE"+counter, make)
            editor.putString("MODEL"+counter, model)

            editor.apply()
            editor.commit()
            loadPreferences()

            //adapter.notifyDataSetChanged()
           // var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
         //   var listLv = findViewById<ListView>(R.id.listLv)

           // listLv.adapter = adapter





            // addItems()


        }



        /*
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

*/
    //Reloads the ListView element
    //Only way to make notifyDataSetChanged work with the ArrayList
    }
    fun loadPreferences(){
        val data = getSharedPreferences(prefName,Context.MODE_PRIVATE);
        var dataSet = data.getString("YEAR"+start, "None Available")
        dataSet += " "+ data.getString("MAKE"+start, null)
        dataSet += " "+ data.getString("MODEL"+start, null)

        adapter.add(dataSet);
        adapter.notifyDataSetChanged()
    }

}
