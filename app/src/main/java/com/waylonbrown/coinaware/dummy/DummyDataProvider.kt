package com.waylonbrown.coinaware.dummy

import com.github.mikephil.charting.data.Entry
import java.util.*

class DummyChartDataProvider {

    data class PortfolioListItem(val data: List<Entry>, val positiveTrend: Int)

    fun getDummyData(): Set<PortfolioListItem> {
        val dataSet = mutableSetOf<PortfolioListItem>()
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
        dataSet.add(PortfolioListItem(getRandomChartData(), Random().nextInt(2)))
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
}

class DummyAlertDataProvider {

    data class AlertListItem(val isHeader: Boolean = false,
                             val headerName: String = "",
                             val currentPrice: Float = 0f,
                             val triggerPrice: Float = 0f,
                             val isPersistent: Boolean = false,
                             val isOn: Boolean = false)

    fun getDummyData(): List<AlertListItem> {
        val dataSet = mutableListOf<AlertListItem>()
        dataSet.add(AlertListItem(isHeader = true, headerName = "ETH", currentPrice = 458.34f))
        dataSet.add(AlertListItem(triggerPrice = 500.0f, isPersistent = false, isOn = true))
        dataSet.add(AlertListItem(triggerPrice = 750.0f, isPersistent = true, isOn = false))
        dataSet.add(AlertListItem(isHeader = true, headerName = "BTC", currentPrice = 8504.23f))
        dataSet.add(AlertListItem(triggerPrice = 8500.0f, isPersistent = false, isOn = true))
        dataSet.add(AlertListItem(isHeader = true, headerName = "LTC", currentPrice = 74.23f))
        dataSet.add(AlertListItem(triggerPrice = 100.0f, isPersistent = false, isOn = true))
        return dataSet
    }
}