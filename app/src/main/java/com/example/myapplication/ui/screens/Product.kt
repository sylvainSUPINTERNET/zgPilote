package com.example.myapplication.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.dto.ProductDto
import com.example.myapplication.api.dto.TaskDto
import com.google.gson.Gson


class Product : AppCompatActivity() {
    lateinit var product: ProductDto
    lateinit var textViewName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val intent = intent
        intent.getStringExtra("product").let { taskFromListAsJson ->
            product = Gson().fromJson(taskFromListAsJson, ProductDto::class.java)
            textViewName = findViewById(R.id.product_profile_name)
            textViewName.text = product.name
        }
    }
}