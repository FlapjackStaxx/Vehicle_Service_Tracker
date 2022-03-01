package com.staxxproducts.vehicleservicetracker.data


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.vehicleservicetracker.R


class ServiceAdapter(val service: List<Service>): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {


    val list = mutableListOf<String>()

     class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val textView = view.findViewById<TextView>(R.id.svcNotesTv)

       /* fun update(index: Int){
            textView.text = list[index]
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = (service[position].serviceDate + " || " +
        service[position].serviceMiles + " || " + service[position].serviceType)
    }

    override fun getItemCount() = service.size


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}