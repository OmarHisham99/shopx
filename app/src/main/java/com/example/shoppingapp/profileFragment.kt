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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewmodel: ProfileViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                profileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
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