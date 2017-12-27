package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.MutableLiveData
import com.waylonbrown.coinaware.io.Resource
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListData

class PortfolioDao {
    
    fun getBTCtoUSDPrice(): MutableLiveData<Resource<PortfolioListData>>? {
        return null
    }
}