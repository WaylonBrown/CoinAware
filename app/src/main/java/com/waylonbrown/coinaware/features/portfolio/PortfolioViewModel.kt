package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.waylonbrown.coinaware.io.Resource
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class PortfolioViewModel : ViewModel() {
    
    private var listData = MutableLiveData<Resource<Float>>()
    private val repository = PortfolioRepository()
    
    fun getDataFromCache() {
        repository.getBTCtoUSDPrice(listData)
    }
    
    fun fetchNewData() {
        repository.fetchNewBTCtoUSDPrice(listData)
    }
    
    // Expose LiveData rather than MutableLiveData
    fun getLiveData(): LiveData<Resource<Float>> = listData
}