package com.waylonbrown.coinaware.screens.portfolio

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem

class PortfolioChartConfig(val context: Context,
                           override val chart: LineChart,
                           val item: PortfolioListItem,
                           val isHeader: Boolean) : ChartConfig<Entry>() {
    
    override val backgroundColor: Int = when {
        isHeader -> ContextCompat.getColor(context, R.color.colorPrimary)
        item.positiveTrend -> ContextCompat.getColor(context, R.color.green)
        else -> ContextCompat.getColor(context, R.color.red)
    }

    override val data = item.data

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
        xAxis.setDrawLabels(false)

        val yAxisLeft = chart.axisLeft
        with(yAxisLeft) {
            setDrawGridLines(false)
            setDrawLabels(false)
            setDrawZeroLine(false)
        }

        val yAxisRight = chart.axisRight
        with(yAxisRight) {
            setDrawGridLines(false)
            setDrawZeroLine(false)
            setDrawLabels(false)
        }

        val legend = chart.legend
        legend.isEnabled = false

        val lineData = LineData(dataSet)
        chart.data = lineData
    }
}