package com.waylonbrown.coinaware.screens.insights

import android.content.Context
import android.support.v4.content.ContextCompat
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.waylonbrown.coinaware.R

// TODO: remove chart config duplication
class InsightsPieChartConfig(val context: Context,
                             val pieChartView: PieChart,
                             val pieChartModel: InsightsPieChart) {
    
    fun apply() {
        val backgroundColor = ContextCompat.getColor(context, R.color.white)
        
        // TODO: do this for performance
//        if (chart.data == null || chart.data.dataSetCount == 0) {
            initializeChart()
//        } else {
//            updateData(backgroundColor)
//        }

        pieChartView.setBackgroundColor(backgroundColor)
    }

    private fun initializeChart() {
        pieChartView.setTouchEnabled(false)
        pieChartView.description = null
        pieChartView.setUsePercentValues(true)
        
        val legend = pieChartView.legend
        legend.isEnabled = false
        
        val coin1 = pieChartModel.coinList[0]
        val coin2 = pieChartModel.coinList[1]
        val coin3 = pieChartModel.coinList[2]
        
        val dataSet = PieDataSet(pieChartModel.coinList, "Unused")
        
        val color1 = ContextCompat.getColor(context, R.color.chartLineBlue)
        val color2 = ContextCompat.getColor(context, R.color.chartLineRed)
        val color3 = ContextCompat.getColor(context, R.color.chartLineYellow)
        dataSet.colors = listOf(color1, color2, color3)

        val pieData = PieData(dataSet)
        pieChartView.data = pieData
    } }