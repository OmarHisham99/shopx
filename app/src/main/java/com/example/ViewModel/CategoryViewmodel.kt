package com.example.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Repository.ProductsRepository
import com.example.application.Category
import com.example.application.Product_Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewmodel(application: Application) : AndroidViewModel(application)
{

    val productsMutable: MutableLiveData<Product_Model?>? = MutableLiveData<Product_Model?>()

    fun getMovies(country:String) {
        ProductsRepository.getINSTANCE()?.getcoordinates(country)?.enqueue(object :
            Callback<Product_Model?> {
            override fun onResponse(
                call: Call<Product_Model?>,
                response: Response<Product_Model?>
            )
            {
                productsMutable?.postValue(response.body())
            }

            override fun onFailure(
                call: Call<Product_Model?>,
                t: Throwable
            )
            {
                Log.v("CategoryViewmodel", t.message.toString());
            }
        })
    }
}