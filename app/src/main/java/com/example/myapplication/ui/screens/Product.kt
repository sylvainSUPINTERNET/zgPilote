package com.example.myapplication.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.dto.CurrencySubChannelsDto
import com.example.myapplication.api.dto.CurrencySubDto
import com.example.myapplication.api.dto.ProductDto
import com.example.myapplication.api.dto.TaskDto
import com.example.myapplication.ws.WSListener
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit


class Product : AppCompatActivity() {
    lateinit var product: ProductDto
    lateinit var textViewName: TextView
    lateinit var currentPriceText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // wss
        val client = OkHttpClient.Builder().readTimeout(3, TimeUnit.SECONDS).build()
        val request = Request.Builder()
            .url(getString(R.string.crypto_api_ws)) // 'wss' - для защищенного канала
            .build()
        val wsListener = WSListener()
        val webSocket = client.newWebSocket(request, wsListener) // this provide to make 'Open ws connection'


        val channelsTarget:CurrencySubChannelsDto = CurrencySubChannelsDto("ticker", arrayOf("BTC-EUR"))
        val subPayload: CurrencySubDto = CurrencySubDto("subscribe", arrayOf(channelsTarget))
        val gson:Gson = Gson();

        webSocket.send(gson.toJson(subPayload))

        val intent = intent
        intent.getStringExtra("product")?.let { taskFromListAsJson ->
            product = Gson().fromJson(taskFromListAsJson, ProductDto::class.java)
            textViewName = findViewById(R.id.product_profile_name)
            textViewName.text = product.name
        }
    }
}