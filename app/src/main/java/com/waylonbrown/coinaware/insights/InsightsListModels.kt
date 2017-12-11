package com.waylonbrown.coinaware.insights

import com.github.mikephil.charting.data.Entry

data class InsightsListItem(val header: InsightsHeader? = null,
                            val graph: InsightsGraph? = null) {
    
    fun isHeader() = header != null
}

data class InsightsHeader(val name: String)

sealed class InsightsGraph

data class InsightsRelativeGraph(val coinList: List<CoinWithPrices>) : InsightsGraph()

data class CoinWithPrices(val name: String, val prices: List<Entry>)