package com.waylonbrown.coinaware.base

interface ChartConfig {
    
    // Applies changes to the chart
    fun apply()
    
    // First-time chart setup
    fun initializeChart()
    
    // Only update chart data if it's been initialized
    fun updateChart()
}