package com.example.shoppingapp.CategoriesPage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.Data
import com.example.shoppingapp.R

class ItemsAdapter (val data: List<Data>?): RecyclerView.Adapter<ItemsAdapter.viewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent,
                false
        )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.viewHolder, position: Int) {
        holder.name.text = data?.get(position)?.name
        holder.price.text = "AED" + data?.get(position)?.price.toString()
        Glide.with(holder.image.getContext())
                .load(data?.get(position)?.image.toString())
                .centerCrop()
                .into(holder.image)
    }


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val name: TextView
        val price: TextView
        val image: ImageView
        init {
            name = itemView.findViewById(R.id.title)
            price = itemView.findViewById(R.id.price)
            image = itemView.findViewById(R.id.itemPhoto)
        }


    }
    override fun getItemCount(): Int {
        if(data!=null) return data.size
        else return 0
    }
}