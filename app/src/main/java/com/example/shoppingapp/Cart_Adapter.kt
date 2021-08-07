package com.example.shoppingapp.CategoriesPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Firestore_objs.Cart_Item
import com.example.ViewModel.Cart_ItemViewmodel
import com.example.application.Data
import com.example.shoppingapp.R

class Cart_Adapter(val data: List<Cart_Item>?): RecyclerView.Adapter<Cart_Adapter.viewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {


        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item,
            parent,
            false
        )
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: Cart_Adapter.viewHolder, position: Int) {
        holder.name.text = data?.get(position)?.name
        holder.price.text = "AED" + data?.get(position)?.price.toString()
        holder.Count.text = data?.get(position)?.count.toString()
        Glide.with(holder.image.getContext())
            .load(data?.get(position)?.Image.toString())
            .centerCrop()
            .into(holder.image)
    }


    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        lateinit var cartItemviewmodel: Cart_ItemViewmodel
        val name: TextView
        val price: TextView
        val image: ImageView
        val Add: ImageButton
        val Remove: ImageButton
        val Count : TextView
        init {
            cartItemviewmodel = Cart_ItemViewmodel()
            name = itemView.findViewById(R.id.title)
            price = itemView.findViewById(R.id.price)
            image = itemView.findViewById(R.id.itemPhoto)
            Add = itemView.findViewById(R.id.add)
            Remove=itemView.findViewById(R.id.remove)
            Count = itemView.findViewById(R.id.count)
            Add.setOnClickListener(this)
            Remove.setOnClickListener(this)
        }

        override fun onClick(p0: View?)
        {

            if(p0?.id == R.id.add)
            {

                val number: Int = Count.text.toString().toInt() + 1
                Count.text = number.toString()

                cartItemviewmodel.AddItem(Cart_Item(data?.get(position)?.id.toString(),data?.get(position)?.name
                    , data?.get(position)?.price.toString(),data?.get(position)?.Image, number
                ))
            }
            else if(p0?.id == R.id.remove)
            {
                var number: Int = Count.text.toString().toInt() - 1
                if(number>=0)
                    Count.text = number.toString()
                else
                    number = 0
                cartItemviewmodel.AddItem(Cart_Item(data?.get(position)?.id.toString(),data?.get(position)?.name
                    , data?.get(position)?.price.toString(),data?.get(position)?.Image, number
                ))
            }
        }
    }

    override fun getItemCount(): Int {
        if(data!=null) return data.size
        else return 0
    }
}