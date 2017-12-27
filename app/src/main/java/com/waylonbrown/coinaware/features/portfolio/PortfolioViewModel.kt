package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider

class PortfolioViewModel : ViewModel() {
    
    var listData: LiveData<DummyPortfolioDataProvider.PortfolioListData>? = null
    
    fun initialize() {
        // Important since this is called multiple times in the lifecycle
        if (listData != null) return
        
        listData = PortfolioRepository().getBTCtoUSDPrice()
    }
    
    fun fetchNewData() {
        listData = PortfolioRepository().fetchBTCtoUSDPrice()
    }

}