package com.test.Cryptotest.data.model

data class GetAssetsResult(
    val data : List< Asset>
)

data class Asset (
    val id : String,
    val name : String,
    val symbol : String,
    val priceUsd : Float,
    val changePercent24Hr : Float
)