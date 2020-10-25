package com.example.myapplication.api.dto

import java.math.BigDecimal
import java.sql.Blob

data class ProductDto (
    val id: Number,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val currency: String,
    val illustration: Blob,
    val quantity: List<String>,
    val stock: Number,
    val createdAt: String,
    val updatedAt: String,
    val categoryId: Number
)