// EntitiesAdapter.kt
package com.example.assigment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntitiesAdapter(
    private val entities: List<Entity>,
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntitiesAdapter.EntityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.bind(entity)
        holder.itemView.setOnClickListener {
            onItemClick(entity)  // Trigger the click event
        }
    }

    override fun getItemCount(): Int = entities.size

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val property1TextView: TextView = itemView.findViewById(R.id.property1TextView)
        private val property2TextView: TextView = itemView.findViewById(R.id.property2TextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(entity: Entity) {
            // Bind property1 and property2 values to the TextViews
            property1TextView.text = entity.property1
            property2TextView.text = entity.property2

            // Optionally show description if needed
            if (entity.description.isNotEmpty()) {
                descriptionTextView.visibility = View.VISIBLE
                descriptionTextView.text = entity.description
            } else {
                descriptionTextView.visibility = View.GONE  // Hide if no description is present
            }
        }
    }
}

