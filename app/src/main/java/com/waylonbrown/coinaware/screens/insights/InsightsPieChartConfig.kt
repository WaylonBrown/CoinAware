package com.waylonbrown.coinaware.screens.insights

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig

// TODO: remove chart config duplication
class InsightsPieChartConfig(val context: Context,
                             override val chart: PieChart,
                             val pieChartModel: InsightsPieChart) : ChartConfig() {
    override fun apply() {
        val backgroundColor = ContextCompat.getColor(context, R.color.white)
        
        // TODO: do this for performance
//        if (chart.data == null || chart.data.dataSetCount == 0) {
            initializeChart()
//        } else {
//            updateChart(backgroundColor)
//        }

        chart.setBackgroundColor(backgroundColor)
    }

    override fun initializeChart() {
        chart.setTouchEnabled(false)
        chart.description = null
        chart.setUsePercentValues(true)
        
        val legend = chart.legend
        legend.isEnabled = false
        
        val dataSet = PieDataSet(pieChartModel.coinList, "Unused")
        
        val color1 = ContextCompat.getColor(context, R.color.chartLineBlue)
        val color2 = ContextCompat.getColor(context, R.color.chartLineRed)
        val color3 = ContextCompat.getColor(context, R.color.chartLineYellow)
        dataSet.colors = listOf(color1, color2, color3)

        val pieData = PieData(dataSet)
        chart.data = pieData
    }

    override fun updateChart() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
