package com.example.myapplication.ui.screens

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.dto.TaskDto
import com.google.gson.Gson


class Product : AppCompatActivity() {
    lateinit var task: TaskDto
    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val intent = intent
        intent.getStringExtra("product").let { taskFromListAsJson ->
            task = Gson().fromJson(taskFromListAsJson, TaskDto::class.java)
            title = findViewById(R.id.product_profile_title)
            title.text = task.title
        }
    }
}