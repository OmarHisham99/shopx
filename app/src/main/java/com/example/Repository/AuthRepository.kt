package com.example.Repository

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository
{
     val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
     val userLiveData: MutableLiveData<FirebaseUser?>? = MutableLiveData<FirebaseUser?>()
     val toast: MutableLiveData<String?>? = MutableLiveData<String?>()
    val toast2: MutableLiveData<String?>? = MutableLiveData<String?>()
    fun login(email:String, pass:String)
   {
       firebaseAuth.signInWithEmailAndPassword(email, pass)
           .addOnCompleteListener(
               OnCompleteListener<AuthResult> { task: Task<AuthResult> -> if (task.isSuccessful())
               {
                   userLiveData!!.postValue(firebaseAuth.currentUser)
               }
               else
               {
                   userLiveData?.setValue(null)
                   toast?.value = task.getException()?.message.toString()
               }
               }
           )
   }
    fun register(email:String, pass:String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
           if(it.isSuccessful)
           {
               userLiveData!!.postValue(firebaseAuth.currentUser)
               toast2?.value = "Registration Successfull: "
           }
           else
           {
               toast2?.value = "Registration Failure: " + it.getException()?.message
           }
        }
    }
}