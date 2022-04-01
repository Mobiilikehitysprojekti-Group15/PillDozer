package com.example.pilldozer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var medicineCount: Int = 0


    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val medicineCountTV: TextView = itemView.findViewById(R.id.tvCount)

        fun bind(medicineCount: Int) {
            medicineCountTV.text = medicineCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(medicineCount)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateMedicineCount(updatedMedicineCount: Int) {
        medicineCount = updatedMedicineCount
        notifyDataSetChanged()
    }

}