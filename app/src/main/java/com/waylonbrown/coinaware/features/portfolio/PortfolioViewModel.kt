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
        // TODO: change back to getting actual price
//        listData = repository.getBTCtoUSDPrice()
        listData.value = Resource.success(124.13f)
    }
    
    fun fetchNewData() {
        listData.value = Resource.success(94.32f)
        // TODO: change back to getting actual price
//        listData = repository.fetchNewBTCtoUSDPrice()
    }
    
    // Expose LiveData rather than MutableLiveData
    fun getLiveData(): LiveData<Resource<Float>> = listData
}