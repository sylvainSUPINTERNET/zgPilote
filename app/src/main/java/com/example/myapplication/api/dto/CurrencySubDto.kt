package com.example.myapplication.api.dto

data class CurrencySubDto (
    val type:String,
    val channels: Array<CurrencySubChannelsDto>
)