package com.example.shoppingapp

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.example.shoppingapp.databinding.FragmentHolderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class holderfrag: Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: FragmentHolderBinding
    lateinit var authStateListener:FirebaseAuth.AuthStateListener
    val user: FirebaseAuth? = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(inflater,
            com.example.shoppingapp.R.layout.fragment_holder,container,false)
        loadFragment(homepage())
        binding.bottom.setOnNavigationItemSelectedListener(this)
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if(user?.currentUser == null)
            {
                findNavController().navigate(com.example.shoppingapp.R.id.action_holderfrag_to_welcomepage)
            }
        }

        user?.addAuthStateListener(authStateListener)
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        user?.removeAuthStateListener(authStateListener)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        var fragment: Fragment? = null

        when (item.itemId) {
           binding.bottom.menu.getItem(0).itemId-> fragment = homepage()
            binding.bottom.menu.getItem(2).itemId-> fragment = profileFragment()
        }

        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            getActivity()?.getSupportFragmentManager()
                ?.beginTransaction()
                ?.replace(binding.fragmentContainer.id, fragment)
                ?.commit()
            return true
        }
        return false
    }
}