package com.staxxproducts.vehicleservicetracker


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class VehicleAdapter (exampleList: ArrayList<VehicleItem>) :
    RecyclerView.Adapter<VehicleAdapter.ExampleViewHolder>() {
    private val mVehicleList: ArrayList<VehicleItem> = exampleList

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewLine1: TextView = itemView.findViewById(R.id.textview_line1)


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return ExampleViewHolder(v)


    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem: VehicleItem = mVehicleList[position]
        (currentItem.year + " " + currentItem.make + " " + currentItem.model).also { holder.mTextViewLine1.text = it }



    }

    override fun getItemCount(): Int {
        return mVehicleList.size
    }






}