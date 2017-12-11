package com.waylonbrown.coinaware.insights

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.insights.InsightsAdapter.ItemType.GRAPH
import com.waylonbrown.coinaware.insights.InsightsAdapter.ItemType.HEADER

// TODO: remove this comment when done
// Viewholders example: https://jonfhancock.com/your-viewholders-are-dumb-make-em-not-dumb-82e6f73f630c
class InsightsAdapter(val layoutInflater: LayoutInflater)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<InsightsListItem> = mutableListOf()

    enum class ItemType {
        HEADER,
        GRAPH
    }

    fun updateItems(data: List<InsightsListItem>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER.ordinal) {
            val view = layoutInflater.inflate(R.layout.insights_item_header, parent, false)
            InsightsHeaderViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.insights_item_relative_chart, parent, false)
            InsightsItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InsightsHeaderViewHolder) {
            holder.setData(items[position])
        } else if (holder is InsightsItemViewHolder) {
            holder.setData(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].isHeader()) {
            return HEADER.ordinal
        } else return GRAPH.ordinal
    }

    override fun getItemCount(): Int = items.size

    class InsightsHeaderViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: InsightsListItem

        // TODO: remove this comment when done
        // Example: 
        // https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CubicLineChartActivity.java
        fun setData(data: InsightsListItem) {
            this.item = data
//
//            // TODO: android extensions
//            val chart = itemView.findViewById<LineChart>(R.id.chart)
//            InsightsRelativeChartConfig(itemView.context, chart, item).apply()
        }
    }

    // TODO: remove duplication
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