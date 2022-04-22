package com.example.pilldozer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.data.Medicine

class CustomAdapter(private val onClick: (Medicine) -> Unit) :
    ListAdapter<Medicine, CustomAdapter.MedicineViewHolder>(MedicineDiffCallback) {

    class MedicineViewHolder(ItemView: View, val onClick: (Medicine) -> Unit) :
        RecyclerView.ViewHolder(ItemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imagePill)
        private val textViewName: TextView = itemView.findViewById(R.id.medName)
        private val textViewQuantity: TextView = itemView.findViewById(R.id.medQuantity)
        //private val textViewDescription: TextView = itemView.findViewById(R.id.medDescription)
        private var currentMedicine: Medicine? = null

        init {
            itemView.setOnClickListener {
                currentMedicine?.let {
                    onClick(it)
                }
            }
        }

        fun bind(medicine: Medicine) {
            currentMedicine = medicine

            textViewName.text = medicine.name
            textViewQuantity.text = medicine.quantity
            //textViewDescription.text = medicine.description
            imageView.setImageResource(medicine.image!!)
        }
    }


    // Luo uusia views:sejä
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return MedicineViewHolder(view, onClick)
    }

    // bindaa listan esineen viewiin
    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {

        val medicine = getItem(position)
        holder.bind(medicine)

    }

    // Palauttaa listalla olevien esineiden määrän
    /*
    override fun getItemCount(): Int {
        return mList.size
    }*/

}

object MedicineDiffCallback : DiffUtil.ItemCallback<Medicine>() {
    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
        return oldItem.id == newItem.id
    }
}