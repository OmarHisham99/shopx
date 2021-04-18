package com.example.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

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