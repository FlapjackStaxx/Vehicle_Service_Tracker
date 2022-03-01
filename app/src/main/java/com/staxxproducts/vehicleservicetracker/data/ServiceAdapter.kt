package com.staxxproducts.vehicleservicetracker.data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.vehicleservicetracker.*


class ServiceAdapter(val service: List<Service>): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {


    val list = mutableListOf<String>()

     class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val textView: TextView = view.findViewById(R.id.serviceItemTextView)

       /* fun update(index: Int){
            textView.text = list[index]
        }*/


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (service[position].serviceDate + " || " +
                service[position].serviceMiles + " || " + service[position].serviceType).also { holder.textView.text = it }
        serviceDate = service[position].serviceDate
        serviceMileage = service[position].serviceMiles
        serviceType = service[position].serviceType
        serviceNotes = service[position].serviceNotes
        serviceId = service[position].serviceId!!




    }

    override fun getItemCount() = service.size


}