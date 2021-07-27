package com.example.shoppingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.Firestore_objs.User
import com.example.ViewModel.LoginViewmodel
import com.example.ViewModel.ProfileViewmodel
import com.example.shoppingapp.databinding.FragmentHolderBinding
import com.example.shoppingapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class profileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewmodel: ProfileViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            com.example.shoppingapp.R.layout.fragment_profile,container,false)
        profileViewmodel = ViewModelProvider(this)[ProfileViewmodel::class.java]
        profileViewmodel.getuserdata()
        binding.logout.setOnClickListener(View.OnClickListener
        {
            profileViewmodel?.signOut()
        })
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewmodel.userLiveData
                ?.observe(viewLifecycleOwner,
                        Observer<User?> { it ->
                            if (it != null)
                            {
                                binding.name.setText(it.firstname)
                            }
                        })
        profileViewmodel.FirebaseuserLiveData
                ?.observe(viewLifecycleOwner,
                        Observer<FirebaseUser?> { it ->
                            if (it == null)
                            {
                                if (findNavController().currentDestination?.id == R.id.profileFragment) {
                                    findNavController().navigate(R.id.action_profileFragment_to_loginpage)
                                }

                            }
                        })





    }
}