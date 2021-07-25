package com.example.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.Firestore_objs.User
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class UserRepository
{
    val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()
    val userLiveData: MutableLiveData<com.example.Firestore_objs.User?>?
            = MutableLiveData<com.example.Firestore_objs.User?>()
    fun setuser(user:User)
    {
        firestore.collection("users").document(user.id!!)
            .set(user)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }

    fun getuser(ID: String)
    {
        val docRef = firestore.collection("users").document(ID!!)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject<com.example.Firestore_objs.User>()
            userLiveData?.postValue(user)
        }
    }
}