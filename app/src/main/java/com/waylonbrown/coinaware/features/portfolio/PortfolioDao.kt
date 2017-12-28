package com.waylonbrown.coinaware.features.portfolio

import com.waylonbrown.coinaware.io.Resource
import javax.inject.Inject

class PortfolioDao @Inject constructor(){
    
    fun getBTCtoUSDPrice(): Resource<Float>? {
        return Resource.success(124.13f)
    }
}