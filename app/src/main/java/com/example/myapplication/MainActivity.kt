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
import com.example.myapplication.ws.WSListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.*
import java.util.concurrent.TimeUnit

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


        /**
         * TEST WS
         */

        // wss test
        val client = OkHttpClient.Builder().readTimeout(3, TimeUnit.SECONDS).build()
        val request = Request.Builder()
            .url("wss://echo.websocket.org") // 'wss' - для защищенного канала
            .build()
        val wsListener = WSListener()
        val webSocket = client.newWebSocket(request, wsListener) // this provide to make 'Open ws connection'


        val productRetrofit = Retrofit.Builder().baseUrl(getString(R.string.api_url)).addConverterFactory(GsonConverterFactory.create()).build()
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
