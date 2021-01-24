package com.example.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoppingapp.databinding.FragmentWelcomepageBinding



class welcomepage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentWelcomepageBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_welcomepage,container,false)
        binding.getstartbtn.setOnClickListener{ view->
            view.findNavController().navigate(R.id.action_welcomepage_to_loginpage)
        }
        return binding.root
    }

}