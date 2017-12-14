package com.waylonbrown.coinaware.screens.portfolio

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter

class PortfolioChartConfig(val context: Context,
                           override val chart: LineChart,
                           val item: PortfolioListItem,
                           val isHeader: Boolean) : ChartConfig() {
    
    private var backgroundColor: Int = when {
        isHeader -> ContextCompat.getColor(context, R.color.colorPrimary)
        item.positiveTrend -> ContextCompat.getColor(context, R.color.green)
        else -> ContextCompat.getColor(context, R.color.red)
    }

    override fun apply() {
        if (!chartIsInitialized()) {
            initializeChart()
        } else {
            updateChart()
        }

        chart.setBackgroundColor(backgroundColor)
    }

    override fun initializeChart() {
        with(chart) {
            setTouchEnabled(false)
            setViewPortOffsets(-1F, -1F, 0F, 0F)
            description = null
            isAutoScaleMinMaxEnabled = true
            setDrawBorders(false)
        }

        val dataSet = LineDataSet(item.data, "Data set test")
        with(dataSet) {
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircles(false)
            setDrawValues(false)
            setDrawFilled(true)
            lineWidth = 0f
            color = backgroundColor
            when {
                isHeader -> fillDrawable = ContextCompat.getDrawable(context, R.drawable.chart_fill_gradient)
                item.positiveTrend -> fillColor = ContextCompat.getColor(context, R.color.greenDark)
                else -> fillColor = ContextCompat.getColor(context, R.color.redDark)
            }
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
            textColor = ContextCompat.getColor(context, R.color.white)
            setDrawGridLines(false)
            setDrawZeroLine(false)
            setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
            setValueFormatter {
                value, axis -> FloatToCurrencyFormatter(value).formatWithDollarSign()
            }
        }

        val legend = chart.legend
        legend.isEnabled = false

        val lineData = LineData(dataSet)
        chart.data = lineData
    }

    /**
     * Only updates data that changes from item to item
     */
    override fun updateChart() {
        val dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
        dataSet.values = item.data
        dataSet.color = backgroundColor
        chart.data.notifyDataChanged()
        chart.notifyDataSetChanged()
    }
}