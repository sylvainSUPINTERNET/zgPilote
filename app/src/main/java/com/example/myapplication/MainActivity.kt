package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.api.dto.TaskDto
import com.example.myapplication.api.services.TodoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                    for (task in response.body()!!) {
                        Log.d("TASK => ", task.title)
                    }
                }
            }
            override fun onFailure(call: Call<List<TaskDto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
