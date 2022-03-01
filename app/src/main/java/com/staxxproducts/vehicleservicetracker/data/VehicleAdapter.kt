package com.staxxproducts.vehicleservicetracker.data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.vehicleservicetracker.R


class VehicleAdapter(val vehicle: List<Vehicle>): RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {


    val list = mutableListOf<String>()

     class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val textView = view.findViewById<TextView>(R.id.vehicleItemTv)

       /* fun update(index: Int){
            textView.text = list[index]
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = (vehicle[position].vehicleYear + " " +
        vehicle[position].vehicleMake + " " + vehicle[position].vehicleModel)
    }

    override fun getItemCount() = vehicle.size


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}