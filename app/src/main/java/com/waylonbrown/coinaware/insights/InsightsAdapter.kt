package com.waylonbrown.coinaware.insights

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.dummy.DummyInsightsDataProvider.InsightsListItem

// TODO: remove this comment when done
// Viewholders example: https://jonfhancock.com/your-viewholders-are-dumb-make-em-not-dumb-82e6f73f630c
class InsightsAdapter(val layoutInflater: LayoutInflater)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: Set<InsightsListItem> = mutableSetOf()

    fun updateItems(data: Set<InsightsListItem>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.insights_relative_chart_item, parent, false)
        return InsightsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as InsightsItemViewHolder).setData(items.elementAt(position))
    }

    override fun getItemCount(): Int = items.size

    class InsightsItemViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: InsightsListItem

        // TODO: remove this comment when done
        // Example: 
        // https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CubicLineChartActivity.java
        fun setData(data: InsightsListItem) {
            this.item = data

            // TODO: android extensions
            val chart = itemView.findViewById<LineChart>(R.id.chart)
            InsightsRelativeChartConfig(itemView.context, chart, item).apply()
        }
    }
}