package com.waylonbrown.coinaware.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.waylonbrown.coinaware.api.model.CoinPrice
import com.waylonbrown.coinaware.api.retrofit.CryptoCompareService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CoinPriceFetcher {
    
    val TAG = CoinPriceFetcher::class.java.simpleName
    
    fun getFromBTCtoUSDPrice(listener: FetchCoinPriceListener) {
        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        val cryptoService = retrofit.create(CryptoCompareService::class.java)
        cryptoService.getCurrencyToCurrencyPrice(from = "BTC", to = "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: SingleObserver<CoinPrice> {
                    override fun onSuccess(value: CoinPrice) {
                        listener.coinPriceFetched(value.price)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(TAG, "Error getting price", e)
                        listener.onFetchFail()
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                })
    }
    
    interface FetchCoinPriceListener {
        fun coinPriceFetched(price: Float)
        
        fun onFetchFail()
    }
}