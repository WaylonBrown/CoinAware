package com.waylonbrown.coinaware.screens.insights

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry

data class InsightsListItem(val header: InsightsHeader? = null,
                            val graph: InsightsGraph? = null) {

    enum class ItemType {
        HEADER,
        RELATIVE_PERFORMANCE_GRAPH,
        ALLOCATIONS_PIE_CHART
    }
    
    fun getItemType(): ItemType {
        if (header != null) {
            return ItemType.HEADER
        }
        if (graph is InsightsRelativeGraph) {
            return ItemType.RELATIVE_PERFORMANCE_GRAPH
        }
        return ItemType.ALLOCATIONS_PIE_CHART
    }
}

data class InsightsHeader(val name: String)

sealed class InsightsGraph

data class InsightsRelativeGraph(val coinList: List<CoinWithPrices>) : InsightsGraph()

data class InsightsPieChart(val coinList: List<PieEntry>) : InsightsGraph()

data class CoinWithPrices(val name: String, val prices: List<Entry>)