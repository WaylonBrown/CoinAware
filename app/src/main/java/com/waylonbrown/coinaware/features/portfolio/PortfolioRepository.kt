package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.MutableLiveData
import com.waylonbrown.coinaware.io.Resource
import com.waylonbrown.coinaware.io.model.CoinPrice
import com.waylonbrown.coinaware.io.retrofit.CryptoCompareService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mu.KotlinLogging
import javax.inject.Inject

private val logger = KotlinLogging.logger {}

class PortfolioRepository @Inject constructor(private val dao: PortfolioDao,
                                              private val cryptoService: CryptoCompareService) {

    /**
     * Return cached data, or fetch new data if nothing cached
     */
    fun getBTCtoUSDPrice(listData: MutableLiveData<Resource<Float>>) {
        
        // TODO: all DAO interaction needs to be on background thread
        val cachedData = dao.getBTCtoUSDPrice()
        if (cachedData != null) {
            listData.value = cachedData
            return
        }
        
        // TODO: want this to update the DAO then get from the DAO here after fetched
        fetchNewBTCtoUSDPrice(listData)
    }

    /**
     * Fetch new data
     */
    fun fetchNewBTCtoUSDPrice(listData: MutableLiveData<Resource<Float>>) {
        cryptoService.getCurrencyToCurrencyPrice(from = "BTC", to = "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: SingleObserver<CoinPrice> {
                    override fun onSuccess(value: CoinPrice) {
//                        returnData.value = 
//                                Resource.success(DummyPortfolioDataProvider().getDummyData())
                        listData.value = Resource.success(94.32f)
                        logger.info { "Value returned: $value"}
                    }

                    override fun onError(e: Throwable?) {
                        listData.value = Resource.error("Couldn't get result")
                        logger.error(e!!) { "Couldn't get result" }
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                })
    }
}