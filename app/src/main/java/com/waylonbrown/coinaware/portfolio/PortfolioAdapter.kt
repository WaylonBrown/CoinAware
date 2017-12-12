package com.waylonbrown.coinaware.portfolio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.data.DummyPortfolioDataProvider.PortfolioListItem
import com.waylonbrown.coinaware.portfolio.PortfolioAdapter.PortfolioHeaderViewHolder.ListItemClickedListener

// TODO: remove this comment when done
// Viewholders example: https://jonfhancock.com/your-viewholders-are-dumb-make-em-not-dumb-82e6f73f630c
class PortfolioAdapter(val layoutInflater: LayoutInflater, 
                       val itemClickedListener: ListItemClickedListener) 
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    var items: Set<PortfolioListItem> = mutableSetOf()

    enum class ItemType {
        HEADER,
        LIST_ITEM
    }

    fun updateItems(data: Set<PortfolioListItem>) {
        this.items = data
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) ItemType.HEADER.ordinal
        else ItemType.LIST_ITEM.ordinal

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ItemType.HEADER.ordinal) {
            val view = layoutInflater.inflate(R.layout.portfolio_item_header, parent, false)
            return PortfolioHeaderViewHolder(view, itemClickedListener)
        } else {
            val view = layoutInflater.inflate(R.layout.portfolio_item, parent, false)
            return PortfolioItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PortfolioHeaderViewHolder) {
            holder.setData(items.elementAt(position))
        } else if (holder is PortfolioItemViewHolder) {
            holder.setData(items.elementAt(position))
        }
    }

    override fun getItemCount(): Int = items.size

    class PortfolioHeaderViewHolder(itemView: View, listener: ListItemClickedListener)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: PortfolioListItem

        interface ListItemClickedListener {
            fun itemClicked(data: PortfolioListItem)
        }

        init {
            itemView.setOnClickListener { listener.itemClicked(item) }
        }

        // TODO: remove this comment when done
        // Example: 
        // https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CubicLineChartActivity.java
        fun setData(data: PortfolioListItem) {
            this.item = data

            val chart = itemView.findViewById<LineChart>(R.id.chart)
            PortfolioChartConfig(itemView.context, chart, item, true).apply()
        }
    }

    // TODO: remove duplication
    class PortfolioItemViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: PortfolioListItem

        // TODO: remove this comment when done
        // Example: 
        // https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/CubicLineChartActivity.java
        fun setData(data: PortfolioListItem) {
            this.item = data

            val chart = itemView.findViewById<LineChart>(R.id.chart)
            PortfolioChartConfig(itemView.context, chart, item, false).apply()
        }
    }
}