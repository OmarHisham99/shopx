package com.example.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.Firestore_objs.Cart_Item
import com.example.ViewModel.Cart_Viewmodel
import com.example.ViewModel.CategoryViewmodel
import com.example.shoppingapp.CategoriesPage.Cart_Adapter
import com.example.shoppingapp.CategoriesPage.ItemsAdapter
import com.example.shoppingapp.HomePage.homepage
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.databinding.FragmentCategoryFragmetBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [cartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class cartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentCartBinding
    lateinit var cartViewmodel: Cart_Viewmodel
    lateinit var  itemsAdapter: Cart_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCartBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_cart,
                container,
                false
            )
        cartViewmodel = Cart_Viewmodel()
        cartViewmodel.getItems()
        binding.itemsList.layoutManager = LinearLayoutManager(
            this.context,
            OrientationHelper.VERTICAL,
            false
        )

        cartViewmodel.CartitemsMutable?.observe(viewLifecycleOwner,

            Observer { string ->
                itemsAdapter = Cart_Adapter(string as List<Cart_Item>?)
                binding.itemsList.adapter = itemsAdapter
            }
        )
        return binding.root
    }

}