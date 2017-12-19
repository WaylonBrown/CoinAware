package com.waylonbrown.coinaware.screens.insights

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.base.ChartConfig

class InsightsPieChartConfig(val context: Context,
                             override val chart: PieChart,
                             val pieChartData: InsightsPieChart) : ChartConfig<PieEntry>() {

    override val backgroundColor = ContextCompat.getColor(context, R.color.white)
    
    override val data = pieChartData.coinList

    override fun initializeChart() {
        with(chart) {
            setTouchEnabled(false)
            description = null
            setUsePercentValues(true)
        }
        
        val legend = chart.legend
        legend.isEnabled = false
        
        val dataSet = PieDataSet(pieChartData.coinList, "Unused")
        
        val color1 = ContextCompat.getColor(context, R.color.chartLineBlue)
        val color2 = ContextCompat.getColor(context, R.color.chartLineRed)
        val color3 = ContextCompat.getColor(context, R.color.chartLineYellow)
        dataSet.colors = listOf(color1, color2, color3)

        val pieData = PieData(dataSet)
        chart.data = pieData
    }
}
