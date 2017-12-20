package com.waylonbrown.coinaware.screens.insights

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig

class InsightsRelativeChartConfig(val context: Context,
                                  override val chart: LineChart,
                                  val relativeGraphData: InsightsRelativeGraph) 
    : ChartConfig<Entry>() {

    override val backgroundColor = ContextCompat.getColor(context, R.color.white)

    override val data = mutableListOf<Entry>()

    override fun initializeChart() {
        chart.setTouchEnabled(false)
        chart.setViewPortOffsets(-1F, -1F, 0F, 0F)
        chart.description = null
        chart.isAutoScaleMinMaxEnabled = true
        chart.setDrawBorders(false)
        
        val dataSetList = mutableListOf<LineDataSet>()
        for((index, coin) in relativeGraphData.coinList.withIndex()) {
            val dataSet = LineDataSet(coin.prices, coin.name)
            setConfigForDataSet(dataSet, index)
            dataSetList.add(dataSet)
        }

        val lineData = LineData(dataSetList.toList())
        chart.data = lineData
    }

    /**
     * Only updates data that changes from item to item
     */
    override fun updateChart() {
//        val dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
//        dataSet.values = data
//        dataSet.color = backgroundColor
//        chart.data.notifyDataChanged()
//        chart.notifyDataSetChanged()
    }

    private fun setConfigForDataSet(dataSet: LineDataSet, index: Int) {
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.setDrawFilled(false)
        dataSet.lineWidth = 3f
        dataSet.color = getColorByIndex(index)

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
    }

    private fun getColorByIndex(index: Int): Int = when(index) {
        1 -> ContextCompat.getColor(context, R.color.chartLineBlue)
        2 -> ContextCompat.getColor(context, R.color.chartLineRed)
        else -> ContextCompat.getColor(context, R.color.chartLineYellow)
    }
}