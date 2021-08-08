package com.example.shoppingapp.LoginPage

import android.app.Application
import android.widget.ProgressBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.example.shoppingapp.LoginPage.loginpage

class LoginViewmodel(application: Application) : AndroidViewModel(application)
{

         val authAppRepository: AuthRepository? = AuthRepository()
     val userLiveData: MutableLiveData<FirebaseUser?>? = authAppRepository?.userLiveData
     val Toast: MutableLiveData<String?>? = authAppRepository?.toast

    fun login(email:String, pass:String,)
    {

        if (authAppRepository != null)
        {
            authAppRepository.login(email, pass)
        }
    }

}