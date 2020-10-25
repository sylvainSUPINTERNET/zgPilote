package com.example.myapplication.api.dto

data class ProductListResponseDto (
    val error: Boolean,
    val message: List<ProductDto>
)