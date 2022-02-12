package com.staxxproducts.vehicleservicetracker


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ServiceAdapter (servicesList: ArrayList<Service>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    private val mServiceItem: ArrayList<Service> = servicesList

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mServiceTextView: TextView = itemView.findViewById(R.id.serviceItemTv)


    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ServiceViewHolder(v)


    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentItem: Service = mServiceItem[position]
        (currentItem.date + "   ||  " + currentItem.mileage + " miles ||   " + currentItem.typeOfService).also { holder.mServiceTextView.text = it }



    }

    override fun getItemCount(): Int {
        return mServiceItem.size
    }






}