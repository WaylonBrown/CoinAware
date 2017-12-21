package com.waylonbrown.coinaware.screens.insights

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.waylonbrown.coinaware.R

class InsightsAdapter(private val layoutInflater: LayoutInflater)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<InsightsListItem> = mutableListOf()

    enum class ItemType {
        HEADER,
        RELATIVE_PERFORMANCE_GRAPH,
        ALLOCATIONS_PIE_CHART
    }

    fun updateItems(data: List<InsightsListItem>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER.ordinal -> {
                val view = layoutInflater.inflate(R.layout.insights_item_header, parent, false)
                InsightsHeaderViewHolder(view)
            }
            ItemType.RELATIVE_PERFORMANCE_GRAPH.ordinal -> {
                val view = layoutInflater.inflate(R.layout.insights_item_relative_chart, parent, false)
                InsightsRelativeChartViewHolder(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.insights_item_pie_chart, parent, false)
                InsightsPieChartViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InsightsHeaderViewHolder -> holder.setData(items[position])
            is InsightsRelativeChartViewHolder -> holder.setData(items[position])
            is InsightsPieChartViewHolder -> holder.setData(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].getItemType().ordinal

    override fun getItemCount(): Int = items.size

    class InsightsHeaderViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        fun setData(data: InsightsListItem) {
            val title = itemView.findViewById(R.id.headerTitle) as TextView
            if (data.header != null) {
                title.text = data.header.name
            }
        }
    }

    class InsightsRelativeChartViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        fun setData(data: InsightsListItem) {
            val chart = itemView.findViewById<LineChart>(R.id.chart)
            InsightsRelativeChartConfig(itemView.context, chart, data.graph as InsightsRelativeGraph)
                    .apply()
        }
    }

    class InsightsPieChartViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        fun setData(data: InsightsListItem) {
            val chart = itemView.findViewById<PieChart>(R.id.chart)
            InsightsPieChartConfig(itemView.context, chart, data.graph as InsightsPieChart).apply()
        }
    }
}