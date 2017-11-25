package com.waylonbrown.coinaware.alerts

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.alerts.AlertsAdapter.AlertItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider.AlertListItem
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter

// TODO: remove this comment when done
// Viewholders example: https://jonfhancock.com/your-viewholders-are-dumb-make-em-not-dumb-82e6f73f630c
class AlertsAdapter(val layoutInflater: LayoutInflater,
                    val itemClickedListener: ListItemClickedListener) 
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<AlertListItem> = mutableListOf()

    enum class ItemType {
        HEADER,
        LIST_ITEM
    }

    fun updateItems(data: List<AlertListItem>) {
        this.items = data
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].isHeader) {
            return ItemType.HEADER.ordinal
        }
        return ItemType.LIST_ITEM.ordinal
    }
    
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemType.HEADER.ordinal) {
            val view = layoutInflater.inflate(R.layout.alert_item_header, parent, false)
            AlertHeaderViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.alert_item, parent, false)
            AlertItemViewHolder(view, itemClickedListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlertHeaderViewHolder) {
            holder.setData(items.elementAt(position))
        } else if (holder is AlertItemViewHolder) {
            holder.setData(items.elementAt(position))
        }
    }

    override fun getItemCount(): Int = items.size

    class AlertHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var item: AlertListItem

        fun setData(data: AlertListItem) {
            this.item = data

            // TODO: android extensions
            val coinTitle = itemView.findViewById<TextView>(R.id.coinTitle)
            val coinPrice = itemView.findViewById<TextView>(R.id.coinPrice)
            
            coinTitle.text = item.headerName
            coinPrice.text = FloatToCurrencyFormatter(item.currentPrice).format()
        }
    }

    // TODO: remove duplication
    class AlertItemViewHolder(itemView: View, listener: ListItemClickedListener) 
        : RecyclerView.ViewHolder(itemView) {

        lateinit var item: AlertListItem

        init {
            itemView.setOnClickListener { listener.itemClicked(item) }
        }
        
        interface ListItemClickedListener {
            fun itemClicked(data: AlertListItem)
        }

        fun setData(data: AlertListItem) {
            this.item = data

            // TODO: android extensions
            val chart = itemView.findViewById<LineChart>(R.id.chart)
        }
    }
}