package com.staxxproducts.servicetracker.data.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.servicetracker.data.entities.Vehicle
import servicetracker.R


class VehicleAdapter(val vehicle: List<Vehicle>): RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {


    val list = mutableListOf<String>()

     class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val textView: TextView = view.findViewById(R.id.vehicleItemTv)

       /* fun update(index: Int){
            textView.text = list[index]
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (vehicle[position].vehicleYear + " " +
                vehicle[position].vehicleMake + " " + vehicle[position].vehicleModel).also { holder.textView.text = it }
    }

    override fun getItemCount() = vehicle.size


}