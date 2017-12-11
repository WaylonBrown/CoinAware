package com.waylonbrown.coinaware.insights

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R

class InsightsRelativeChartConfig(val context: Context,
                                  val chart: LineChart,
                                  val item: InsightsListItem) {
    
    fun apply() {
        val backgroundColor = ContextCompat.getColor(context, R.color.white)
        
        // TODO: do this for performance
//        if (chart.data == null || chart.data.dataSetCount == 0) {
            initializeChart()
//        } else {
//            updateData(backgroundColor)
//        }

        chart.setBackgroundColor(backgroundColor)
    }

    private fun initializeChart() {
        chart.setTouchEnabled(false)
        chart.setViewPortOffsets(-1F, -1F, 0F, 0F)
        chart.description = null
        chart.isAutoScaleMinMaxEnabled = true
        chart.setDrawBorders(false)
        
        val coin1 = (item.graph as InsightsRelativeGraph).coinList[0]
        val coin2 = item.graph.coinList[1]
        val coin3 = item.graph.coinList[2]

        val coin1DataSet = LineDataSet(coin1.prices, coin1.name)
        val coin2DataSet = LineDataSet(coin2.prices, coin2.name)
        val coin3DataSet = LineDataSet(coin3.prices, coin3.name)
        
        setConfigForDataSet(coin1DataSet, ContextCompat.getColor(context, R.color.chartLineBlue))
        setConfigForDataSet(coin2DataSet, ContextCompat.getColor(context, R.color.chartLineRed))
        setConfigForDataSet(coin3DataSet, ContextCompat.getColor(context, R.color.chartLineYellow))

        val lineData = LineData(listOf(coin1DataSet, coin2DataSet, coin3DataSet))
        chart.data = lineData
    }

    private fun setConfigForDataSet(dataSet: LineDataSet, @ColorInt lineColor: Int) {
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.setDrawFilled(false)
        dataSet.lineWidth = 3f
        dataSet.color = lineColor

        // TODO: do the below for each one or just once?
        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)

        val yAxisLeft = chart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.setDrawLabels(false)
        yAxisLeft.setDrawZeroLine(false)

        val yAxisRight = chart.axisRight
        yAxisRight.textColor = ContextCompat.getColor(context, R.color.darkBackground)
        yAxisRight.setDrawGridLines(false)
        yAxisRight.setDrawZeroLine(false)
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yAxisRight.setDrawLabels(false)

        val legend = chart.legend
        legend.isEnabled = false
//        // TODO: use resources (DP)
//        legend.textSize = 16f
//        legend.isWordWrapEnabled = true
//        legend.setDrawInside(false)
    }

//    private fun updateData(backgroundColor: Int) {
//        val dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
//        dataSet.values = item.data
//        dataSet.color = backgroundColor
//        chart.data.notifyDataChanged()
//        chart.notifyDataSetChanged()
//    }
}