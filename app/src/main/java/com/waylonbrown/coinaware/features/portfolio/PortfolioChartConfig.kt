package com.waylonbrown.coinaware.features.portfolio

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig
import com.waylonbrown.coinaware.features.portfolio.PortfolioChartConfig.Type.*

class PortfolioChartConfig(private val context: Context,
                           override val chart: LineChart,
                           val item: PortfolioListItem) : ChartConfig<Entry>() {
    
    private val type = getItemType()
    
    override val backgroundColor: Int = when(type) {
        HEADER -> ContextCompat.getColor(context, R.color.colorPrimary)
        POSITIVE_ITEM -> ContextCompat.getColor(context, R.color.green)
        NEGATIVE_ITEM -> ContextCompat.getColor(context, R.color.red)
    }
    
    override val data = when(type) {
        HEADER -> item.header!!.data
        else -> item.item!!.data
    }

    override fun initializeChart() {
        with(chart) {
            setTouchEnabled(false)
            setViewPortOffsets(-1F, -1F, 0F, 0F)
            description = null
            isAutoScaleMinMaxEnabled = true
            setDrawBorders(false)
        }

        val dataSet = LineDataSet(data, "Data set test")
        with(dataSet) {
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircles(false)
            setDrawValues(false)
            setDrawFilled(true)
            lineWidth = 0f
            color = backgroundColor
            when(type) {
                HEADER -> fillDrawable = ContextCompat
                        .getDrawable(context, R.drawable.chart_fill_gradient)
                POSITIVE_ITEM -> fillColor = ContextCompat.getColor(context, R.color.greenDark)
                NEGATIVE_ITEM -> fillColor = ContextCompat.getColor(context, R.color.redDark)
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
    
    enum class Type {
        HEADER,
        POSITIVE_ITEM,
        NEGATIVE_ITEM
    }
    
    private fun getItemType(): Type = when {
        item.header != null -> HEADER
        item.item != null && item.item.positiveTrend -> POSITIVE_ITEM
        else -> NEGATIVE_ITEM
    }
}