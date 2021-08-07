package com.example.shoppingapp.CategoriesPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.ViewModel.CategoryViewmodel
import com.example.shoppingapp.HomePage.homepage
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentCategoryFragmetBinding

class CategoryFragmet : Fragment() {
    lateinit var binding: FragmentCategoryFragmetBinding
    lateinit var categoryViewmodel: CategoryViewmodel

    lateinit var  itemsAdapter:ItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val binding: FragmentCategoryFragmetBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_category_fragmet,
                    container,
                    false
                )
        categoryViewmodel = ViewModelProvider(this)[CategoryViewmodel::class.java]
        val arguments = arguments
        val desired_string = arguments!!.getString("Category")
        categoryViewmodel.getproducts("*$desired_string*")
        binding.CategroyName.text = arguments!!.getString("Category")
        binding.itemsList.layoutManager = LinearLayoutManager(
            this.context,
            OrientationHelper.VERTICAL,
            false
        )
        binding.backBtn.setOnClickListener {
            val manager: FragmentManager? = fragmentManager
            val transaction: FragmentTransaction? = manager?.beginTransaction()
            val fragment = homepage()

            transaction?.replace(R.id.fragment_container,fragment)
            transaction?.commit()
        }
        categoryViewmodel.productsMutable?.observe(viewLifecycleOwner,

            Observer { string ->
                itemsAdapter = ItemsAdapter(string?.data)
                binding.itemsList.adapter = itemsAdapter
            }
        )
        return binding.root
    }


    }
