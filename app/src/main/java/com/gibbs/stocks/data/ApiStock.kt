package com.gibbs.stocks.data

import com.google.gson.annotations.SerializedName

data class ApiStock(
    val ticker: String?,
    val name: String?,
    val currency: String?,
    @SerializedName("current_price_cents") val currentPriceCents: Int?,
    val quantity: Int?,
    @SerializedName("current_price_timestamp") val currentPriceTimestamp: Int?,
)
