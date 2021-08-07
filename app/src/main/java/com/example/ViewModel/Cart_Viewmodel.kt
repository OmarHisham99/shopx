package com.example.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Firestore_objs.Cart_Item
import com.example.Repository.Cart_ItemsRepo
import com.google.firebase.auth.FirebaseUser

class Cart_Viewmodel:ViewModel()
{
    val cartitemsrepo: Cart_ItemsRepo? = Cart_ItemsRepo()
    val CartitemsMutable: MutableLiveData<List<Cart_Item?>>? = cartitemsrepo?.userLiveData
    fun getItems()
    {
     cartitemsrepo?.getItems()
    }

}