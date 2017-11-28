package com.waylonbrown.coinaware.portfolio

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.dummy.DummyChartDataProvider.PortfolioListItem
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter

class PortfolioChartConfig(val context: Context,
                           val chart: LineChart,
                           val item: PortfolioListItem,
                           val isHeader: Boolean) {
    
    fun apply() {
        val backgroundColor = when {
            isHeader -> ContextCompat.getColor(context, R.color.colorPrimary)
            item.positiveTrend == 1 -> ContextCompat.getColor(context, R.color.green)
            else -> ContextCompat.getColor(context, R.color.red)
        }
        
        if (chart.data == null || chart.data.dataSetCount == 0) {
            initializeChart(backgroundColor)
        } else {
            updateData(backgroundColor)
        }

        chart.setBackgroundColor(backgroundColor)
    }

    private fun initializeChart(backgroundColor: Int) {
        chart.setTouchEnabled(false)
        chart.setViewPortOffsets(-1F, -1F, 0F, 0F)
        chart.description = null
        chart.isAutoScaleMinMaxEnabled = true
        chart.setDrawBorders(false)

        val dataSet = LineDataSet(item.data, "Data set test")
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
//        dataSet.cubicIntensity = 0.2f
        dataSet.setDrawFilled(true)
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 0f
        dataSet.color = backgroundColor
        dataSet.fillDrawable = when {
            isHeader -> ContextCompat.getDrawable(context, R.drawable.chart_fill_gradient)
            item.positiveTrend == 1 -> ContextCompat.getDrawable(context, R.drawable.chart_fill_gradient_positive)
            else -> ContextCompat.getDrawable(context, R.drawable.chart_fill_gradient_negative)
        }


        val xAxis = chart.xAxis
        xAxis.setDrawGridLines(false)

        val yAxisLeft = chart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.setDrawLabels(false)
        yAxisLeft.setDrawZeroLine(false)

        val yAxisRight = chart.axisRight
        yAxisRight.textColor = ContextCompat.getColor(context, R.color.white)
        yAxisRight.setDrawGridLines(false)
        yAxisRight.setDrawZeroLine(false)
        yAxisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        yAxisRight.setValueFormatter {
            value, axis -> FloatToCurrencyFormatter(value).format()
        }

        val legend = chart.legend
        legend.isEnabled = false

        val lineData = LineData(dataSet)
        chart.data = lineData
    }

    private fun updateData(backgroundColor: Int) {
        val dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
        dataSet.values = item.data
        dataSet.color = backgroundColor
        chart.data.notifyDataChanged()
        chart.notifyDataSetChanged()
    }
}