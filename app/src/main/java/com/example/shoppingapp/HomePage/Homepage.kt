package com.example.shoppingapp.HomePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.arlib.floatingsearchview.FloatingSearchView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomepageBinding
import java.util.ArrayList


class homepage : Fragment() {

    lateinit var  imageslider: ImageSlider
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
        imageslider.setImageList(imgs,ScaleTypes.CENTER_CROP )




        //items recyclerview creating
        val categoryItem = arrayOf(R.drawable.furniture,R.drawable.decorations,R.drawable.personal_care,
        R.drawable.women_clothes,R.drawable.electronics,R.drawable.baby_products,R.drawable.musical_instruments,R.drawable.books,R.drawable.lights)

        val categoryName = arrayOf("Furniture","Decorations","Personal Care", "Women Clothes","Electronics","Baby Products","Musical","Books","lights")

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context,OrientationHelper.HORIZONTAL,false)
        binding.recyclerView.adapter = recyclerViewAdapter(categoryItem,categoryName)



        // Inflate the layout for this fragment
        return binding.root
    }

}