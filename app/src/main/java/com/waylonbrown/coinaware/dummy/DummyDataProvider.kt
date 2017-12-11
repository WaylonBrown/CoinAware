package com.waylonbrown.coinaware.dummy

import com.github.mikephil.charting.data.Entry
import com.waylonbrown.coinaware.alerts.Alert
import com.waylonbrown.coinaware.alerts.AlertHeader
import com.waylonbrown.coinaware.alerts.AlertListItem
import com.waylonbrown.coinaware.alerts.AlertTrigger
import com.waylonbrown.coinaware.insights.CoinWithPrices
import com.waylonbrown.coinaware.insights.InsightsHeader
import com.waylonbrown.coinaware.insights.InsightsListItem
import com.waylonbrown.coinaware.insights.InsightsRelativeGraph
import java.util.*

class DummyPortfolioDataProvider {

    data class PortfolioListItem(val data: List<Entry>, val positiveTrend: Boolean)

    fun getDummyData(): Set<PortfolioListItem> {
        val dataSet = mutableSetOf<PortfolioListItem>()
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        dataSet.add(PortfolioListItem(getRandomChartData(), randTrend()))
        return dataSet
    }
    
    private fun getRandomChartData(): List<Entry> = when (randNumber()) {
        0 -> randSet1()
        1 -> randSet2()
        else -> randSet3()
    }

    private fun randSet1(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 10F))
        list.add(Entry(1F, 8F))
        list.add(Entry(2F, 7F))
        list.add(Entry(3F, 8F))
        list.add(Entry(4F, 7F))
        list.add(Entry(5F, 7F))
        list.add(Entry(6F, 10F))
        list.add(Entry(7F, 12F))
        list.add(Entry(8F, 13F))
        list.add(Entry(9F, 17F))
        list.add(Entry(10F, 16F))
        list.add(Entry(11F, 15F))
        list.add(Entry(12F, 12F))
        list.add(Entry(13F, 10F))
        list.add(Entry(14F, 11F))
        list.add(Entry(15F, 11F))
        list.add(Entry(16F, 12F))
        list.add(Entry(17F, 13F))
        list.add(Entry(18F, 12F))
        list.add(Entry(19F, 10F))
        list.add(Entry(20F, 8F))
        return list
    }

    private fun randSet2(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 2F))
        list.add(Entry(1F, 2.5F))
        list.add(Entry(2F, 2.3F))
        list.add(Entry(3F, 2F))
        list.add(Entry(4F, 3F))
        list.add(Entry(5F, 3.2F))
        list.add(Entry(6F, 2.75F))
        list.add(Entry(7F, 4.7F))
        list.add(Entry(8F, 6.78F))
        list.add(Entry(9F, 6.88F))
        list.add(Entry(10F, 6.98F))
        list.add(Entry(11F, 5.8F))
        list.add(Entry(12F, 5.3F))
        list.add(Entry(13F, 5.5F))
        list.add(Entry(14F, 5.8F))
        list.add(Entry(15F, 6.47F))
        list.add(Entry(16F, 8.2F))
        list.add(Entry(17F, 9.3F))
        list.add(Entry(18F, 11F))
        list.add(Entry(19F, 10F))
        list.add(Entry(20F, 9F))
        return list
    }

    private fun randSet3(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, .1F))
        list.add(Entry(1F, .2F))
        list.add(Entry(2F, .4F))
        list.add(Entry(3F, .77F))
        list.add(Entry(4F, .98F))
        list.add(Entry(5F, 1.3F))
        list.add(Entry(6F, 1.6F))
        list.add(Entry(7F, 1.3F))
        list.add(Entry(8F, 1.2F))
        list.add(Entry(9F, 1.32F))
        list.add(Entry(10F, 1.24F))
        list.add(Entry(11F, .9F))
        list.add(Entry(12F, .6F))
        list.add(Entry(13F, .7F))
        list.add(Entry(14F, .5F))
        list.add(Entry(15F, .35F))
        list.add(Entry(16F, .4F))
        list.add(Entry(17F, .32F))
        list.add(Entry(18F, .28F))
        list.add(Entry(19F, .27F))
        list.add(Entry(20F, .1F))
        return list
    }

    private fun randNumber() = Random().nextInt((2 - 0) + 1)
    
    private fun randTrend(): Boolean {
        if (Random().nextInt(2) == 0) {
            return true
        }
        return false
    }
}

class DummyInsightsDataProvider {

    fun getDummyData(): List<InsightsListItem> {
        val coin1Prices = getChartData1()
        val coin1 = CoinWithPrices("BTC", coin1Prices)
        val coin2Prices = getChartData2()
        val coin2 = CoinWithPrices("ETH", coin2Prices)
        val coin3Prices = getChartData3()
        val coin3 = CoinWithPrices("LTC", coin3Prices)
        
        val header = InsightsHeader("Relative Coin Performance")
        val graph = InsightsRelativeGraph(listOf(coin1, coin2, coin3))
        
        return listOf(InsightsListItem(header = header), 
                InsightsListItem(graph = graph))
    }

    private fun getChartData1(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 10F))
        list.add(Entry(1F, 8F))
        list.add(Entry(2F, 7F))
        list.add(Entry(3F, 8F))
        list.add(Entry(4F, 7F))
        list.add(Entry(5F, 7F))
        list.add(Entry(6F, 10F))
        list.add(Entry(7F, 12F))
        list.add(Entry(8F, 13F))
        list.add(Entry(9F, 17F))
        list.add(Entry(10F, 16F))
        list.add(Entry(11F, 15F))
        list.add(Entry(12F, 12F))
        list.add(Entry(13F, 10F))
        list.add(Entry(14F, 11F))
        list.add(Entry(15F, 11F))
        list.add(Entry(16F, 12F))
        list.add(Entry(17F, 13F))
        list.add(Entry(18F, 12F))
        list.add(Entry(19F, 10F))
        list.add(Entry(20F, 8F))
        return list
    }

    private fun getChartData2(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 8F))
        list.add(Entry(1F, 9F))
        list.add(Entry(2F, 10F))
        list.add(Entry(3F, 10F))
        list.add(Entry(4F, 8F))
        list.add(Entry(5F, 10F))
        list.add(Entry(6F, 12F))
        list.add(Entry(7F, 14F))
        list.add(Entry(8F, 13F))
        list.add(Entry(9F, 12F))
        list.add(Entry(10F, 11F))
        list.add(Entry(11F, 9F))
        list.add(Entry(12F, 10F))
        list.add(Entry(13F, 11F))
        list.add(Entry(14F, 13F))
        list.add(Entry(15F, 15F))
        list.add(Entry(16F, 16F))
        list.add(Entry(17F, 16F))
        list.add(Entry(18F, 15F))
        list.add(Entry(19F, 16F))
        list.add(Entry(20F, 15F))
        return list
    }

    private fun getChartData3(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 17F))
        list.add(Entry(1F, 18F))
        list.add(Entry(2F, 16F))
        list.add(Entry(3F, 17F))
        list.add(Entry(4F, 18F))
        list.add(Entry(5F, 19F))
        list.add(Entry(6F, 19F))
        list.add(Entry(7F, 17F))
        list.add(Entry(8F, 14F))
        list.add(Entry(9F, 9F))
        list.add(Entry(10F, 10F))
        list.add(Entry(11F, 12F))
        list.add(Entry(12F, 11F))
        list.add(Entry(13F, 10F))
        list.add(Entry(14F, 12F))
        list.add(Entry(15F, 13F))
        list.add(Entry(16F, 14F))
        list.add(Entry(17F, 12F))
        list.add(Entry(18F, 14F))
        list.add(Entry(19F, 17F))
        list.add(Entry(20F, 19F))
        return list
    }
}

class DummyAlertsDataProvider {

    fun getDummyData(): List<AlertListItem> {
        val dataSet = mutableListOf<AlertListItem>()
        
        dataSet.add(AlertListItem(header = AlertHeader("ETH", 454.23f)))
        dataSet.add(AlertListItem(item = Alert(AlertTrigger(AlertTrigger.Type.VALUE,
                positive = true,
                triggerAmount = 500f), recurring = true, active = true)))
        dataSet.add(AlertListItem(item = Alert(AlertTrigger(AlertTrigger.Type.CHANGE,
                positive = false,
                triggerAmount = 10f), recurring = false, active = false)))

        dataSet.add(AlertListItem(header = AlertHeader("BTC", 9023.23f)))
        dataSet.add(AlertListItem(item = Alert(AlertTrigger(AlertTrigger.Type.VALUE,
                positive = false,
                triggerAmount = 10000f), recurring = true, active = true)))

        dataSet.add(AlertListItem(header = AlertHeader("LTC", 87.43f)))
        dataSet.add(AlertListItem(item = Alert(AlertTrigger(AlertTrigger.Type.CHANGE,
                positive = true,
                triggerAmount = 25f), recurring = false, active = true)))
        return dataSet
    }
}