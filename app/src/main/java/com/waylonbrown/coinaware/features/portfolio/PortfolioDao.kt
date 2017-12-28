package com.waylonbrown.coinaware.features.portfolio

import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider
import javax.inject.Inject

class PortfolioDao @Inject constructor(){
    
    fun getBTCtoUSDPrice(): PortfolioListData? {
        // TODO: figure out why this thread check fails
//        check(!isOnMainThread()) { "Can't call from main thread" }
        return DummyPortfolioDataProvider().getDummyData()
    }
}