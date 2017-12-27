package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider

class PortfolioViewModel : ViewModel() {
    
    var listData: LiveData<DummyPortfolioDataProvider.PortfolioListData>? = null
    private val repository = PortfolioRepository()
    
    fun initialize() {
        // Important since this is called multiple times in the lifecycle
        if (listData != null) return
        
        listData = repository.getCachedBTCtoUSDPrice()
    }
    
    fun fetchNewData() {
        listData = repository.getFreshBTCtoUSDPrice()
    }

}