package com.example.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Firestore_objs.Cart_Item
import com.example.Repository.AuthRepository
import com.example.Repository.Cart_ItemsRepo
import com.example.Repository.ProductsRepository
import com.example.application.Category
import com.example.application.Product_Model
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Cart_ItemViewmodel : ViewModel()
{
    val cartitemsrepo: Cart_ItemsRepo? = Cart_ItemsRepo()
//    fun getItems(ID:String)
//    {
//        cartitemsrepo?.getItems(ID)
//    }
fun AddItem(cartItem: Cart_Item)
{
    if(cartItem.count== 0)
        cartitemsrepo?.delete(cartItem.id.toString())
     else
        cartitemsrepo?.additem(cartItem)
}

}