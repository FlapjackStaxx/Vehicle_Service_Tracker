package com.staxxproducts.servicetracker.data.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.staxxproducts.servicetracker.data.entities.User
import servicetracker.R


class UserAdapter(val user: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    val list = mutableListOf<String>()

     class ViewHolder(view:View) : RecyclerView.ViewHolder(view){

        val textView: TextView = view.findViewById(R.id.userItemTV)

       /* fun update(index: Int){
            textView.text = list[index]
        }*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (user[position].userName + " " + user[position].userPassword) + user[position].userType.also{ holder.textView.text = it }
    }

    override fun getItemCount() = user.size


}