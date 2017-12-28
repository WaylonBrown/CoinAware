package com.waylonbrown.coinaware.features.portfolio

import com.waylonbrown.coinaware.io.retrofit.CryptoCompareService
import io.reactivex.Single
import javax.inject.Inject

class PortfolioRepository @Inject constructor(private val dao: PortfolioDao,
                                              private val cryptoService: CryptoCompareService) {

    /**
     * Return cached data, or fetch new data if nothing cached
     */
    fun getBTCtoUSDPrice(): Single<PortfolioListData> {
        
        val cachedData = dao.getBTCtoUSDPrice()
        if (cachedData != null) {
            return Single.just(cachedData)
        }
        
        // TODO: want this to update the DAO then get from the DAO here after fetched
        return fetchNewBTCtoUSDPrice()
    }

    /**
     * Fetch new data
     */
    fun fetchNewBTCtoUSDPrice(): Single<PortfolioListData> {
        return cryptoService.getCurrencyToCurrencyPrice(from = "BTC", to = "USD")
                .map { coinPrice -> dao.getBTCtoUSDPrice() }
    }
}