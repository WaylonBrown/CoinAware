package com.waylonbrown.coinaware.base

import com.github.mikephil.charting.charts.Chart

abstract class ChartConfig {
    
    abstract val chart: Chart<*>

    /**
     * Public method to apply changes to the chart
     */
    abstract fun apply()

    /**
     * First-time chart setup
     */
    abstract fun initializeChart()

    /**
     * Only update chart data if it's been initialized
     */
    abstract fun updateChart()
    
    fun chartIsInitialized() = chart.data != null && chart.data.dataSetCount > 0
}