package com.waylonbrown.coinaware.base

import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet

abstract class ChartConfig<out T>
    where T : Entry {
    
    abstract val chart: Chart<*>
    
    abstract val backgroundColor: Int
    
    abstract val data: List<T>
    
    /**
     * Public method to apply changes to the chart
     */
    fun apply() {
        if (!chartIsInitialized()) {
            initializeChart()
        } else {
            updateChart()
        }

        chart.setBackgroundColor(backgroundColor)
    }

    /**
     * First-time chart setup, only call from this class
     */
    abstract fun initializeChart()

    /**
     * Only updates data that changes from item to item. Override if it's not a typical list with
     * one data set.
     */
    open fun updateChart() {
        val dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
        dataSet.values = data
        dataSet.color = backgroundColor
        chart.data.notifyDataChanged()
        chart.notifyDataSetChanged()
    }
    
    private fun chartIsInitialized() = chart.data != null && chart.data.dataSetCount > 0
}