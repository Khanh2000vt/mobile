package com.example.myapplication.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Donation

class AdapterData(private var listDonations: List<Donation>) : RecyclerView.Adapter<AdapterData.ViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Donation>) {
        listDonations = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterData.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterData.ViewHolder, position: Int) {
        holder.onBindView(position)
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var methodView: TextView
        var amountView: TextView
        init {
            methodView = view.findViewById(R.id.typeText)
            amountView = view.findViewById(R.id.textAmount)
        }
        fun onBindView(position: Int) {
            methodView.text = listDonations[position].method
            amountView.text = listDonations[position].amount.toString()
        }
    }

    override fun getItemCount(): Int {
        return listDonations.size
    }

}