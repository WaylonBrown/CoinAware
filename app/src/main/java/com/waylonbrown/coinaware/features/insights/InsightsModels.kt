package com.waylonbrown.coinaware.features.insights

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
import com.waylonbrown.coinaware.features.insights.InsightsAdapter.ItemType

data class InsightsListItem(val header: InsightsHeader? = null,
                            val graph: InsightsGraph? = null) {
    
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