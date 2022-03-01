package com.staxxproducts.vehicleservicetracker

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.staxxproducts.vehicleservicetracker.data.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


class AddVehicle: AppCompatActivity(){


    // Declare Global Variables
    private var listYear: MutableList<String> = ArrayList()

    private val fromYear: String = "2022"
    private val toYear: String = "1900"
    var dateString: String = ""
    var serviceNotes: String = ""
    var typeOfService: String = ""
    var currentMileage = ""



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_vehicle)



     //   val dao = VehicleDatabase.getInstance(applicationContext).vehicleDao


        val make = findViewById<EditText>(R.id.makeEt)
        val model = findViewById<EditText>(R.id.modelEt)
        // Declare Local Variables
        val yearSpin = findViewById<Spinner>(R.id.yearSpin)
        val adapterSpin = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listYear)
        val buttonSave = findViewById<Button>(R.id.addButton)



        // Get range of years for spinner
        listYear = getYearRange(fromYear, toYear)
        yearSpin.adapter = adapterSpin
        showDialog()

        buttonSave.setOnClickListener {


            val timestamp = Date()

            dateString = timestamp.dateToString("MM-dd-yyyy")
            serviceNotes = "Added Vehicle To List"
            typeOfService = "Added Vehicle"



            thread {



                val vehicleAdd = listOf(
                    Vehicle(null,yearSpin.selectedItem.toString(),
                        make.text.toString(),model.text.toString()))
                 db.vehicleDao().insertVehicle(vehicleAdd)


                // Sends user back to Existing Vehicle screen
               /* val intent = Intent(this, ExistingVehicle::class.java)
                startActivity(intent)*/


            }
        }
    }


    private fun showDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Current Mileage")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.hint = "Enter Text"
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
        // Here you get get input text from the Edittext
            currentMileage = input.text.toString()
        })
        builder.show()
    }



    private fun getYearRange(startYear: String, endYear: String): MutableList<String> {
        var cur = startYear.toInt()
        val stop = endYear.toInt()
        while (cur >= stop) {
            listYear.add(cur--.toString())
        }
        return listYear
    }



    // Returns the current date
    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }
}


    /*
        // Runs loadData to populate the list of vehicles
        loadData()


        // Declare Local Variables
        val yearSpin = findViewById<Spinner>(R.id.yearSpin)
        val adapterSpin = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, listYear)
        val buttonSave = findViewById<Button>(R.id.addButton)

        // Get range of years for spinner
        listYear = getYearRange(fromYear, toYear)
        yearSpin.adapter = adapterSpin

        // Upon clicking Save getMoreInfo gets the newly added vehicle's mileage
        buttonSave.setOnClickListener { getMoreInfo() }
    }

    //Adds data for a new vehicle
    private fun saveData() {

        val year = findViewById<Spinner>(R.id.yearSpin)
        val make = findViewById<EditText>(R.id.makeEt)
        val model = findViewById<EditText>(R.id.modelEt)

        // Sets the current date and time as first entry
        val timestamp = Date()
        val dateString = timestamp.dateToString("MM-dd-yyyy")
        val serviceNotes = "Added Vehicle To List"
        val typeOfService = "Added Vehicle"

        mServiceList!!.add(Service(dateString, currentMileage, serviceNotes, typeOfService))

        // insertItem creates strings of the year make and model along with default / current service info and adds it to the vehicles data
        insertItem(year.selectedItem.toString(), make.text.toString(), model.text.toString(), mServiceList!!)

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(mVehicleList1)
        editor.putString("vehicle list", json)
        editor.apply()

        // Sends user back to Existing Vehicle screen
        val intent = Intent(this, ExistingVehicle::class.java)
        startActivity(intent)

    }

    // Creates a dialog box to enter vehicles current mileage then executes saveData to push data to the list
    private fun getMoreInfo() {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            builder.setTitle("Enter Current Info")
            val dialogLayout = inflater.inflate(R.layout.more_info_popup, null)
            val currentMiles  = dialogLayout.findViewById<EditText>(R.id.currentMilesEt)
            val btnOkay = dialogLayout.findViewById<Button>(R.id.btnok)
            builder.setView(dialogLayout)
            btnOkay.setOnClickListener { currentMileage = currentMiles.text.toString()

                //Executes saveData to add vehicle inputs to Gson
                saveData()
            }

            builder.show()


        }

    // Populates the list of vehicles and their services for display
    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("vehicle list", null)
        val type: Type = object : TypeToken<ArrayList<VehicleServiceItem?>?>() {}.type

        mVehicleList1 = gson.fromJson(json, type)
        if (mVehicleList1 == null) {
            mVehicleList1 = ArrayList()
        }

        if (mServiceList == null) {
            mServiceList = ArrayList()
        }
    }



    private fun insertItem(Year: String, Make: String, Model: String, mServiceList: ArrayList<Service>) {
        mVehicleList1!!.add(VehicleServiceItem(Year,Make,Model,mServiceList))
    }

    private fun getYearRange(startYear: String, endYear: String): MutableList<String> {
        var cur = startYear.toInt()
        val stop = endYear.toInt()
        while (cur >= stop) {
            listYear.add(cur--.toString())
        }
        return listYear
    }


    // Returns the current date
    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }
*/
