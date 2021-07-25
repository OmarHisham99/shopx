package com.example.shoppingapp.HomePage

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shoppingapp.Adapter
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomepageBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_homepage.*
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

        // val adp = Adapter(imgs)
        //val pager : ViewPager2 = binding.imagslider
         //pager.adapter=adp
           // val tab:TabLayout = binding.tabLayout
            //TabLayoutMediator(tab,pager){tab,posistion -> }.attach()


        //items recyclerview creating
        val categoryItem = arrayOf(arrayOf(R.drawable.chair,R.drawable.carpet,R.drawable.bedding),
        arrayOf(R.drawable.furniture,R.drawable.decorations,R.drawable.personal_care))

        val categoryName = arrayOf(arrayOf("Chair","Carpet","Bedding"),
        arrayOf("Furniture","Decorations","Presonal Care"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context,OrientationHelper.HORIZONTAL,false)
        binding.recyclerView.adapter = recyclerViewAdapter(categoryItem,categoryName)



        // Inflate the layout for this fragment
        return binding.root
    }

}