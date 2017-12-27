package com.waylonbrown.coinaware.features.insights

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
        with(chart) {
            setTouchEnabled(false)
            setViewPortOffsets(-1F, -1F, 0F, 0F)
            description = null
            isAutoScaleMinMaxEnabled = true
            setDrawBorders(false)
        }

        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)

        val yAxisLeft = chart.axisLeft
        with(yAxisLeft) {
            setDrawGridLines(false)
            setDrawLabels(false)
            setDrawZeroLine(false)
        }

        val yAxisRight = chart.axisRight
        with(yAxisRight) {
            textColor = ContextCompat.getColor(context, R.color.darkBackground)
            setDrawGridLines(false)
            setDrawZeroLine(false)
            setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
            setDrawLabels(false)
        }

        val legend = chart.legend
        legend.isEnabled = false
        
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
        initializeChart()
        // TODO: try to update instead of getDataFromCache every time
//        chart.data = chart.data.dataSets.toMutableList(). 
//        for((index, dataSet) in chart.data.dataSets.withIndex()) {
//            val lineDataSet = dataSet as LineDataSet
//            lineDataSet.values = relativeGraphData.coinList[index].prices
//            lineDataSet.color = getColorByIndex(index)
//        }
//        chart.data.notifyDataChanged()
//        chart.notifyDataSetChanged()
    }

    private fun setConfigForDataSet(dataSet: LineDataSet, index: Int) = with(dataSet) {
        mode = LineDataSet.Mode.CUBIC_BEZIER
        setDrawCircles(false)
        setDrawValues(false)
        setDrawFilled(false)
        lineWidth = 3f
        color = getColorByIndex(index)
    }

    private fun getColorByIndex(index: Int): Int = when(index) {
        1 -> ContextCompat.getColor(context, R.color.chartLineBlue)
        2 -> ContextCompat.getColor(context, R.color.chartLineRed)
        else -> ContextCompat.getColor(context, R.color.chartLineYellow)
    }
}