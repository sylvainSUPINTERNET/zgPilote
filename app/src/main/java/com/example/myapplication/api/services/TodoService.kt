package com.example.myapplication.api.services

import com.example.myapplication.api.dto.TaskDto
import retrofit2.Call
import retrofit2.http.GET

interface TodoService {
    @GET("todos")
    fun getTodos(): Call<List<TaskDto>>
}