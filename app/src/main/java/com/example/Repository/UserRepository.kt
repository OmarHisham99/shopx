package com.example.Repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.Firestore_objs.User
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository
{
    val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()

    fun setuser(user:User)
    {
        firestore.collection("users").document(user.id!!)
            .set(user)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }
}