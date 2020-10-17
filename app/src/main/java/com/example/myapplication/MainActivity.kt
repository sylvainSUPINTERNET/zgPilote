package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.dto.TaskDto
import com.example.myapplication.api.services.TodoService
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


    fun goToProductProfile (task: TaskDto) {
        val intent = Intent(this, Product::class.java)
        intent.putExtra("product", Gson().toJson(task))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.tasksList)


        val retrofit =
            Retrofit
                .Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val todoService = retrofit.create(TodoService::class.java)
        val call = todoService.getTodos()

        call.enqueue(object : Callback<List<TaskDto>> {
            override fun onResponse(call: Call<List<TaskDto>>, response: Response<List<TaskDto>>) {
                if (response.isSuccessful){
                    //Log.d("test", response.body()!!.toString())
                    //for (task in response.body()!!) {
                        //Log.d("TASK => ", task.title)
                    //}
                    val adapter = HomeAdapter(this@MainActivity, response.body()!!)
                    listView.adapter = adapter
                    listView.setOnItemClickListener { parent, view, position, id ->
                        val clickedTask = adapter.getItemAtPosition(position) // The item that was clicked
                        goToProductProfile(clickedTask)
                    }
                }
            }
            override fun onFailure(call: Call<List<TaskDto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
