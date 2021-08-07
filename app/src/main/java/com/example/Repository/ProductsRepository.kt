package com.example.Repository

import com.example.Interface.Product_Interface
import com.example.application.Category
import com.example.application.Product_Model
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsRepository
{
    val Url:String = "http://192.168.1.6:3030/"
    private var getCat: Product_Interface? = null



    init {
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        getCat = retrofit.create(Product_Interface::class.java)

    }
    companion object {
        private val TAG = "Model"
        public var instance: ProductsRepository? = null
        fun getINSTANCE(): ProductsRepository? {
            if (instance == null) {

                instance =
                    ProductsRepository()
            }
            return instance
        }
    }

    fun getcoordinates(Category:String): Call<Product_Model?>?
    {
        return getCat?.getproducts(Category,20)
    }

}