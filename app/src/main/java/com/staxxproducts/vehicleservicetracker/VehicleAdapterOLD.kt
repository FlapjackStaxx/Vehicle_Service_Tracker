/*
package com.staxxproducts.vehicleservicetracker


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class VehicleAdapterOLD (vehicleList: ArrayList<VehicleServiceItem>) :
    RecyclerView.Adapter<VehicleAdapterOLD.VehicleViewHolder>() {
    private val mVehicleList: ArrayList<VehicleServiceItem> = vehicleList

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mVehicleTextView: TextView = itemView.findViewById(R.id.textview_line1)


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return VehicleViewHolder(v)


    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val currentItem: VehicleServiceItem = mVehicleList[position]
        (currentItem.Year + " " + currentItem.Make + " " + currentItem.Model).also { holder.mVehicleTextView.text = it }



    }

    override fun getItemCount(): Int {
        return mVehicleList.size
    }






}*/
