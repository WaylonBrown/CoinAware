package com.waylonbrown.coinaware.api.model

import com.squareup.moshi.Json

class CoinPrice(@Json(name = "USD") val price: Float)