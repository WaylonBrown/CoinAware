package com.waylonbrown.coinaware.features.portfolio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class PortfolioAdapter(private val layoutInflater: LayoutInflater,
                       private val headerClickedListener: PortfolioHeaderViewHolder.ListHeaderClickedListener,
                       private val itemClickedListener: PortfolioItemViewHolder.ListItemClickedListener) 
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    private var items: List<PortfolioListItem> = mutableListOf()

    enum class ItemType {
        HEADER,
        LIST_ITEM
    }

    fun updateItems(data: PortfolioListData) {
        this.items = data.items
        // TODO: diffutil
        notifyDataSetChanged()
    }

    fun updatePortfolioValue(data: Float) {
        val header = items[0].header
        if (!items.isEmpty() && header != null) {
            header.portfolioValue = data
            notifyItemChanged(0)
        } else {
            logger.error { "First list item wasn't the header, couldn't update value" }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (items[position].header != null) ItemType.HEADER.ordinal
        else ItemType.LIST_ITEM.ordinal

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemType.HEADER.ordinal) {
            val view = layoutInflater.inflate(R.layout.portfolio_item_header, parent, false)
            PortfolioHeaderViewHolder(view, headerClickedListener)
        } else {
            val view = layoutInflater.inflate(R.layout.portfolio_item, parent, false)
            PortfolioItemViewHolder(view, itemClickedListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PortfolioHeaderViewHolder) {
            holder.setData(items[position])
        } else if (holder is PortfolioItemViewHolder) {
            holder.setData(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    class PortfolioHeaderViewHolder(itemView: View, listener: ListHeaderClickedListener)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: PortfolioListItem
        
        interface ListHeaderClickedListener {
            fun headerClicked(data: PortfolioListItem)
        }

        init {
            itemView.setOnClickListener { listener.headerClicked(item) }
        }
        
        fun setData(data: PortfolioListItem) {
            this.item = data

            val chart = itemView.findViewById(R.id.chart) as LineChart
            val portfolioValue = itemView.findViewById(R.id.totalPortfolioValue) as TextView
            
            PortfolioChartConfig(itemView.context, chart, item).apply()
            portfolioValue.text = FloatToCurrencyFormatter(item.header!!.portfolioValue)
                    .formatWithDollarSign()
        }

    }

    class PortfolioItemViewHolder(itemView: View, listener: ListItemClickedListener)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: PortfolioListItem
        
        interface ListItemClickedListener {
            fun itemClicked(data: PortfolioListItem)
        }

        init {
            itemView.setOnClickListener { listener.itemClicked(item) }
        }
        
        fun setData(data: PortfolioListItem) {
            this.item = data

            val chart = itemView.findViewById<LineChart>(R.id.chart)
            PortfolioChartConfig(itemView.context, chart, item).apply()
        }

    }
}