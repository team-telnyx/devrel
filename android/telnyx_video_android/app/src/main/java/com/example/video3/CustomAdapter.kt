package com.example.video3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        // sets the User name to the Text View from our itemHolder class
        holder.texViewName.text = itemsViewModel.textRoomName

        // sets the text to the textview from our itemHolder class
        holder.textViewParticipant.text = itemsViewModel.textParticipant
    }
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val texViewName : TextView = itemView.findViewById(R.id.tvCardRoomName)
        val textViewParticipant : TextView = itemView.findViewById(R.id.tvCardParticipants)
    }
}