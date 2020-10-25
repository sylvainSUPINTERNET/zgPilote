package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.dto.ProductDto
import com.example.myapplication.api.dto.ProductListResponseDto
import com.example.myapplication.api.dto.TaskDto
import com.example.myapplication.api.services.ProductService
import com.example.myapplication.ui.HomeAdapter
import com.example.myapplication.ui.screens.Product
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView


    fun goToProductProfile (productDto: ProductDto) {
        val intent = Intent(this, Product::class.java)
        intent.putExtra("product", Gson().toJson(productDto))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.tasksList)

        val productRetrofit = Retrofit.Builder().baseUrl("https://sour-owl-70.loca.lt/api/v1/").addConverterFactory(GsonConverterFactory.create()).build()
        val productService = productRetrofit.create(ProductService::class.java)
        val callProductService = productService.getProducts()

        callProductService.enqueue(object : Callback<ProductListResponseDto> {
            override fun onResponse(call: Call<ProductListResponseDto>, response: Response<ProductListResponseDto>) { //Response<List<ProductDto>>
                if ( response.isSuccessful ) {
                    response.body().let { data ->
                        if ( !data!!.error ) {
                            val products: List<ProductDto> = data.message
                            val adapter = HomeAdapter(this@MainActivity, products)
                            listView.adapter = adapter
                            listView.setOnItemClickListener { parent, view, position, id ->
                                val clickedTask = adapter.getItemAtPosition(position) // The item that was clicked
                                goToProductProfile(clickedTask)
                            }
                        } else {
                            val errorMessageToast = "Une erreur est survenue"
                            Toast.makeText(this@MainActivity, errorMessageToast, Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val errorMessageToast = "Erreur l'api n'a pas répondu"
                    Toast.makeText(this@MainActivity, errorMessageToast, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ProductListResponseDto>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
