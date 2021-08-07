package com.example.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.Firestore_objs.Cart_Item
import com.example.Firestore_objs.User
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class Cart_ItemsRepo
{
    val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()
    val userLiveData: MutableLiveData<List<Cart_Item?>>?
            = MutableLiveData<List<Cart_Item?>>()
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun additem(cartItem:Cart_Item)
    {
        cartItem.userid = firebaseAuth.uid!!
        firestore.collection("Carts").document(firebaseAuth.uid!! + cartItem.id)
            .set(cartItem)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
    fun delete(ID:String)
    {

        firestore.collection("Carts").document(firebaseAuth.uid!! + ID)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
    fun getItems() {
        firestore.collection("Carts")
            .whereEqualTo("userid", firebaseAuth.uid!!)
            .get()
            .addOnSuccessListener { documents ->
                val temp: MutableList<Cart_Item?>?
                     =  ArrayList()
                for (document in documents) {
                    temp?.add(document.toObject(Cart_Item::class.java))
                }
                userLiveData?.postValue(temp)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }

}