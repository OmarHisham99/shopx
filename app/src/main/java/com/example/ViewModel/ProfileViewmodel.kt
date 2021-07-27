package com.example.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Firestore_objs.User
import com.example.Repository.AuthRepository
import com.example.Repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class ProfileViewmodel(application: Application) : AndroidViewModel(application)
{
    val authrepo: AuthRepository =  AuthRepository()
    val userrepo: UserRepository = UserRepository()
    val FirebaseuserLiveData: MutableLiveData<FirebaseUser?>? = authrepo?.userLiveData
    val userLiveData: MutableLiveData<com.example.Firestore_objs.User?>?
            = userrepo?.userLiveData
    fun getuserdata()
    {
        userrepo.getuser(authrepo.firebaseAuth.currentUser!!.uid)
    }

    fun signOut() {
        authrepo.signOut()
    }


}