package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListData

class PortfolioDao {
    
    fun getBTCtoUSDPrice(): LiveData<PortfolioListData> {
        val dummyData = DummyPortfolioDataProvider().getDummyData()
        val liveData = MutableLiveData<PortfolioListData>()
        liveData.value = dummyData
        return liveData
    }
}