package com.example.myapplication.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.api.dto.TaskDto
import com.google.gson.Gson


class Product : AppCompatActivity() {
    lateinit var task: TaskDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val intent = intent
        intent.getStringExtra("product").let { taskFromListAsJson -> task = Gson().fromJson(
            taskFromListAsJson,
            TaskDto::class.java
        )



        }

        Log.d("INTENT PRODUCT : ", task.title)

    }
}