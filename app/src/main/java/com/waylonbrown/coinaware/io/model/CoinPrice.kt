package com.waylonbrown.coinaware.io.model

import com.squareup.moshi.Json

class CoinPrice(@Json(name = "USD") val price: Float)