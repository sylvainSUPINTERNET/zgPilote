package com.example.myapplication.api.adapters

data class CoinWsAdapter (
    val type:String,
    val sequence: Number,
    val productId: String,
    val price: Float,
    val open24h: Float,
    val volume24h: Float,
    val low24h: Float,
    val high24: Float,
    val volume30d: Float,
    val bestBide: Float,
    val bestAsk: Float,
    val side: String,
    val time: String,
    val tradeId: Number,
    val lastSize: Float
)