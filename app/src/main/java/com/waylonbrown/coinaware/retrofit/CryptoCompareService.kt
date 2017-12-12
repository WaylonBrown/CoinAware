package com.waylonbrown.coinaware.retrofit

import com.waylonbrown.coinaware.model.CoinPrice
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareService {
    
    @GET("/data/price")
    fun getCurrencyToCurrencyPrice(@Query("fsym") from: String, @Query("tsyms") to: String)
            : Single<CoinPrice>
}