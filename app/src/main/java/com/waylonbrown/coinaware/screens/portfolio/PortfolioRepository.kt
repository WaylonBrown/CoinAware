package com.waylonbrown.coinaware.screens.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.waylonbrown.coinaware.api.model.CoinPrice
import com.waylonbrown.coinaware.api.retrofit.CryptoCompareService
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val logger = KotlinLogging.logger {}

class PortfolioRepository {

    private var cryptoService: CryptoCompareService? = null

    fun getBTCtoUSDPrice(): LiveData<List<PortfolioListItem>> {
        
        val returnData = MutableLiveData<List<PortfolioListItem>>()
        
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

        cryptoService = retrofit.create(CryptoCompareService::class.java)
        (cryptoService as CryptoCompareService).getCurrencyToCurrencyPrice(from = "BTC", to = "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: SingleObserver<CoinPrice> {
                    override fun onSuccess(value: CoinPrice) {
                        returnData.value = DummyPortfolioDataProvider().getDummyData()
                        logger.info { "Value returned: $value"}
                    }

                    override fun onError(e: Throwable?) {
                        returnData.value = null
                        logger.error(e!!) { "Couldn't get result" }
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                })
        
        return returnData
    }
}