package com.example.myapplication.api.services

import com.example.myapplication.api.dto.ProductDto
import com.example.myapplication.api.dto.ProductListResponseDto
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products/")
    fun getProducts(): Call<ProductListResponseDto>
}