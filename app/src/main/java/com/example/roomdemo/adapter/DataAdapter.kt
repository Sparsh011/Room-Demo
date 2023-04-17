package com.example.roomdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.database.User

class DataAdapter : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(user: User?) {
            val tvFirstName = itemView.findViewById<TextView>(R.id.tv_first_name)
            val tvLastName= itemView.findViewById<TextView>(R.id.tv_last_name)
            val tvUserName = itemView.findViewById<TextView>(R.id.tv_username)
            val tvAge = itemView.findViewById<TextView>(R.id.tv_age)

            tvFirstName.text = "First Name -> " + user?.firstName
            tvLastName.text = "Last Name -> " + user?.lastName
            tvUserName.text = "UserName -> " + user?.username
            tvAge.text = "Age -> " + user?.age.toString()
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }
}