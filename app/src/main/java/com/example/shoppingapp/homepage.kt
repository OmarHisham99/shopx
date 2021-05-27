package com.example.shoppingapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.shoppingapp.databinding.FragmentHomepageBinding


class homepage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomepageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false)
        setHasOptionsMenu(true)

        var imgs= listOf<Int>(R.drawable.img1,R.drawable.img2,R.drawable.img3)
         val adp = Adapter(imgs)
         binding.imagslider.adapter=adp

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

}