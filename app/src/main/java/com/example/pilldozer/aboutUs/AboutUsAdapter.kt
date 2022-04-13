package com.example.pilldozer.aboutUs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pilldozer.R
import com.example.pilldozer.data.Medicine

class AboutUsAdapter(private val onClick: (Person) -> Unit) :
    ListAdapter<Person, AboutUsAdapter.PersonViewHolder>(PersonDiffCallback) {


    class PersonViewHolder(ItemView: View, val onClick: (Person) -> Unit) :
        RecyclerView.ViewHolder(ItemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imagePerson)
        private val textViewName: TextView = itemView.findViewById(R.id.tvPersonName)
        private val textViewDescription: TextView = itemView.findViewById(R.id.tvPersonDescription)

        private var currentPerson: Person? = null

        init {
            itemView.setOnClickListener {
                currentPerson?.let {
                    onClick(it)
                }
            }
        }

        fun bind(person: Person) {
            currentPerson = person

            textViewName.text = person.personName
            textViewDescription.text = person.personDescription

            imageView.setImageResource(person.personImage!!)
        }
    }


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.about_us_card, parent, false)

        return PersonViewHolder(view, onClick)
    }


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)
        holder.bind(person)
    }
}

object PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }
}
