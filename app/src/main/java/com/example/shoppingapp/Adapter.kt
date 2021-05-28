package com.example.shoppingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class Adapter(val images:List<Int>): RecyclerView.Adapter<Adapter.ViewPagerViewHolder>() {
     inner class ViewPagerViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage= images[position]
        holder.itemView.image.setImageResource(curImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }


}