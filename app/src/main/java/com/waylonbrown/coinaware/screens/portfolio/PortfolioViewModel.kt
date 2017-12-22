package com.waylonbrown.coinaware.screens.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem

class PortfolioViewModel : ViewModel() {
    
    var listData: LiveData<List<PortfolioListItem>>? = null
    
    fun initialize() {
        if (listData != null) return
        
        listData = PortfolioRepository().getBTCtoUSDPrice()
    }

}