package com.waylonbrown.coinaware.screens.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem

class PortfolioViewModel : ViewModel() {
    
    lateinit var listData: LiveData<List<PortfolioListItem>>
    
    fun initialize() {
        listData = PortfolioRepository().getBTCtoUSDPrice()
    }


}