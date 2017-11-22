package com.waylonbrown.coinaware

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.waylonbrown.coinaware.PortfolioAdapter.PortfolioHeaderViewHolder
import com.waylonbrown.coinaware.PortfolioAdapter.PortfolioHeaderViewHolder.ListItemClickedListener

class PortfolioAdapter(val layoutInflater: LayoutInflater, 
                       val itemClickedListener: ListItemClickedListener) 
    : RecyclerView.Adapter<PortfolioHeaderViewHolder>() {
    
    var items: Set<DummyHeaderListData> = mutableSetOf()

    enum class ItemType {
        HEADER,
        LIST_ITEM
    }

    fun updateItems(data: DummyHeaderListData) {
        this.items = mutableSetOf(data)
    }

    override fun getItemViewType(position: Int): Int =
            if (position == 0) ItemType.HEADER.ordinal
            else ItemType.LIST_ITEM.ordinal

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PortfolioHeaderViewHolder {
        if (viewType == ItemType.HEADER.ordinal) {
            val view =  layoutInflater.inflate(R.layout.portfolio_item_header, parent, false)
            return PortfolioHeaderViewHolder(view, itemClickedListener)
        } else {
            return PortfolioHeaderViewHolder(View(layoutInflater.context), itemClickedListener)
        }
    }

    override fun onBindViewHolder(holder: PortfolioHeaderViewHolder, position: Int) {
        holder.setData(items.elementAt(position))
    }

    override fun getItemCount(): Int {
        return 1
    }

    class PortfolioHeaderViewHolder(itemView: View, listener: ListItemClickedListener)
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: DummyHeaderListData

        interface ListItemClickedListener {
            fun itemClicked(data: DummyHeaderListData)
        }

        init {
            itemView.setOnClickListener { listener.itemClicked(item) }
        }
        
        fun setData(data: DummyHeaderListData) {
            this.item = data
            // TODO: android extensions
            val chart = itemView.findViewById<LineChart>(R.id.chart)
//            chart.setViewPortOffsets(0, 0, 0, 0)
            
            val dataSet = LineDataSet(item.data, "Data set test")
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            
            val lineData = LineData(dataSet)
            chart.data = lineData
            chart.invalidate()
        }
    }
}