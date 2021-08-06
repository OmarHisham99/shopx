 package com.example.shoppingapp.HomePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shoppingapp.CategoriesPage.CategoryFragmet
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomepageBinding
import java.util.*


 class homepage : Fragment() {

    lateinit var  imageslider: ImageSlider
     lateinit var  recyclerviewAdapter: recyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomepageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)
        setHasOptionsMenu(true)


        //ImageSlider Implementaion
        imageslider = binding.imageSlider
        val imgs  = ArrayList<SlideModel>()
        imgs.add(SlideModel(R.drawable.img1))
        imgs.add(SlideModel(R.drawable.img2))
        imgs.add(SlideModel(R.drawable.img3))
        imageslider.setImageList(imgs, ScaleTypes.CENTER_CROP)



        //items recyclerview creating
        val categoryItem = arrayOf(
            R.drawable.furniture,
            R.drawable.decorations,
            R.drawable.personal_care,
            R.drawable.women_clothes,
            R.drawable.electronics,
            R.drawable.baby_products,
            R.drawable.musical_instruments,
            R.drawable.books,
            R.drawable.lights
        )

        val categoryName = arrayOf(
            "Furniture",
            "Decor",
            "care",
            "T-Shirt",
            "Electronics",
            "Products",
            "Musical",
            "Books",
            "lights"
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(
            this.context,
            OrientationHelper.HORIZONTAL,
            false
        )
        recyclerviewAdapter = recyclerViewAdapter(categoryItem, categoryName)
        recyclerviewAdapter.setOnItemClickListener(object : recyclerViewAdapter.ClickListener {
            override fun onItemClick(position: Int, v: View?) {
                val manager: FragmentManager? = fragmentManager
                val transaction: FragmentTransaction? = manager?.beginTransaction()
                val fragment = CategoryFragmet()
                val arguments = Bundle()
                arguments.putString("Category", categoryName[position])
                fragment.setArguments(arguments)
                transaction?.replace(R.id.fragment_container,fragment)
                transaction?.commit()
            }
        })
        binding.recyclerView.adapter = recyclerviewAdapter

        // Inflate the layout for this fragment
        return binding.root
    }

}