package com.example.shoppingapp.HomePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R


class recyclerViewAdapter(val ci: Array<Int>, val cn: Array<String>): RecyclerView.Adapter<recyclerViewAdapter.viewHolder>(){
    companion object {
        public var clickListener: ClickListener? = null
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
    val view :View = LayoutInflater.from(parent.context).inflate(
        R.layout.categories_items,
        parent,
        false
    )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: recyclerViewAdapter.viewHolder, position: Int) {
        holder.itemname1.text = cn[position]
        holder.itempic1.setImageResource(ci[position])

    }

    override fun getItemCount() = ci.size

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener
    {
        init {
            itemView.setOnClickListener(this)
        }
        val itemname1: TextView = itemView.findViewById(R.id.itemName1)
        val itempic1: ImageButton = itemView.findViewById(R.id.itemPic1)
        override fun onClick(p0: View?) {
            clickListener?.onItemClick(adapterPosition , p0);
        }
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        recyclerViewAdapter.clickListener = clickListener
    }
    interface ClickListener {
        fun onItemClick(position: Int, v: View?)
    }
}


