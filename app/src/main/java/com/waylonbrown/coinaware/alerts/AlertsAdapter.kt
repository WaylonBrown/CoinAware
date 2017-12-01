package com.waylonbrown.coinaware.alerts

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.alerts.AlertsAdapter.AlertItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider.Alert
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider.AlertListItem
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider.AlertTrigger.Type.CHANGE
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
        if (items[position].isHeader()) {
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
            // Null check has already happened
            holder.setData(items.elementAt(position).header!!)
        } else if (holder is AlertItemViewHolder) {
            // Null check has already happened
            holder.setData(items.elementAt(position).item!!)
        }
    }

    override fun getItemCount(): Int = items.size

    class AlertHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: need to keep value here? Same for all other holders
        lateinit var header: DummyAlertDataProvider.AlertHeader

        fun setData(header: DummyAlertDataProvider.AlertHeader) {
            this.header = header

            // TODO: android extensions
            val coinTitle = itemView.findViewById(R.id.coinTitle) as TextView
            val coinPrice = itemView.findViewById(R.id.coinPrice) as TextView
            
            coinTitle.text = header.name
            coinPrice.text = FloatToCurrencyFormatter(header.currentPrice).format()
        }
    }

    // TODO: remove duplication
    class AlertItemViewHolder(itemView: View, listener: ListItemClickedListener) 
        : RecyclerView.ViewHolder(itemView) {

        lateinit var alert: Alert

        init {
            itemView.setOnClickListener { listener.itemClicked(alert) }
        }
        
        interface ListItemClickedListener {
            fun itemClicked(data: Alert)
        }

        fun setData(alert: Alert) {
            this.alert = alert

            // TODO: android extensions
            val triggerText = itemView.findViewById(R.id.trigger) as TextView
            val recurringText = itemView.findViewById(R.id.recurring) as TextView
            val toggleButton = itemView.findViewById(R.id.toggleButton) as SwitchCompat
            
            triggerText.text = buildTriggerText()
            recurringText.text = when {
                alert.recurring -> "Recurring"
                else -> "1 time"
            }
            toggleButton.isChecked = when {
                alert.active -> true
                else -> false
            }
        }

        private fun buildTriggerText(): String {
            val trigger = alert.trigger
            var returnText = if (trigger.type == CHANGE) {
                when {
                    trigger.positive -> "+"
                    else -> "-"
                }
            } else {
                when {
                    trigger.positive -> ">"
                    else -> "<"
                }
            }
            
            returnText += FloatToCurrencyFormatter(alert.trigger.triggerAmount).format()
            
            if (trigger.type == CHANGE) {
                returnText += "%"
            }
            
            return returnText
        }
    }
}