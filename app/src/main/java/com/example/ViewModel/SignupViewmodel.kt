package com.example.ViewModel

import android.app.Application
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Firestore_objs.User
import com.example.Repository.AuthRepository
import com.example.Repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class SignupViewmodel(application: Application) : AndroidViewModel(application)
{
    val authrepo: AuthRepository =  AuthRepository()
    val userrepo:UserRepository= UserRepository()
    val toast: MutableLiveData<String?>? = authrepo?.toast2
    val userLiveData: MutableLiveData<FirebaseUser?>? = authrepo?.userLiveData

    fun insertuser(user:User)
    {
        userrepo.setuser(user)
    }

    fun register(user: User, pass:String)
    {
        var success = true
        if (TextUtils.isEmpty(user.email)) {
            success = false
            Toast.makeText(
                getApplication<Application>().applicationContext,
                "Please Enter the Email !",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (TextUtils.isEmpty(user.firstname) || TextUtils.isEmpty(user.lastname)) {
            success = false
            Toast.makeText(
                getApplication<Application>().applicationContext,
                "Please Enter full name !",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (TextUtils.isEmpty(pass)) {
            success = false
            Toast.makeText(
                getApplication<Application>().applicationContext,
                "Please Enter the password !",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (pass.length < 6) {
            success = false
            Toast.makeText(
                getApplication<Application>().applicationContext,
                "the password must be greater than 6 character !",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (success)
            authrepo.register(user.email,pass)
    }
}