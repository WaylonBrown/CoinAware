package com.waylonbrown.coinaware

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class PortfolioChartConfig(val chart: LineChart, val item: DummyHeaderListData) {
    
    fun apply() {
        chart.setViewPortOffsets(0F, 0F, 0F, 0F)
        chart.description = null

        val dataSet = LineDataSet(item.data, "Data set test")
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)

        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)

        val yAxisLeft = chart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.setDrawLabels(false)
        yAxisLeft.setDrawZeroLine(false)

        val yAxisRight = chart.axisRight
        yAxisRight.setDrawGridLines(false)
        yAxisRight.setDrawZeroLine(false)
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)

        val legend = chart.legend
        legend.isEnabled = false

        val lineData = LineData(dataSet)
        chart.data = lineData
    }
}