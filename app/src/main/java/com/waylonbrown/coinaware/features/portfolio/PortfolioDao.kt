package com.waylonbrown.coinaware.features.portfolio

import com.waylonbrown.coinaware.io.Resource

class PortfolioDao {
    
    fun getBTCtoUSDPrice(): Resource<Float>? {
        return Resource.success(124.13f)
    }
}