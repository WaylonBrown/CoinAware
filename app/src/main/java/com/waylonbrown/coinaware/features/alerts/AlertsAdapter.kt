package com.waylonbrown.coinaware.features.alerts

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.features.alerts.AlertTrigger.Type
import com.waylonbrown.coinaware.features.alerts.AlertsAdapter.AlertItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter

class AlertsAdapter(private val layoutInflater: LayoutInflater,
                    private val itemClickedListener: ListItemClickedListener) 
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<AlertListItem> = mutableListOf()

    enum class ItemType {
        HEADER,
        LIST_ITEM
    }

    fun updateItems(data: List<AlertListItem>) {
        this.items = data
    }

    override fun getItemViewType(position: Int): Int = 
        if (items[position].isHeader()) ItemType.HEADER.ordinal
        else ItemType.LIST_ITEM.ordinal
    
    
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
            holder.setData(items[position].header!!)
        } else if (holder is AlertItemViewHolder) {
            holder.setData(items[position].item!!, isDividerVisibleAtPosition(position))
        }
    }

    override fun getItemCount(): Int = items.size

    private fun isDividerVisibleAtPosition(position: Int): Boolean {
        if (position == items.lastIndex ||
                items[position + 1].isHeader()) {
            return false
        }
        return true
    }

    class AlertHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(header: AlertHeader) {
            val coinTitle = itemView.findViewById(R.id.coinTitle) as TextView
            val coinPrice = itemView.findViewById(R.id.coinPrice) as TextView
            
            coinTitle.text = header.name
            coinPrice.text = FloatToCurrencyFormatter(header.currentPrice).formatWithDollarSign()
        }
    }

    class AlertItemViewHolder(itemView: View, listener: ListItemClickedListener) 
        : RecyclerView.ViewHolder(itemView) {

        lateinit var alert: Alert

        init {
            itemView.setOnClickListener { listener.itemClicked(alert) }
        }
        
        interface ListItemClickedListener {
            fun itemClicked(data: Alert)
        }

        fun setData(alert: Alert, dividerVisible: Boolean) {
            this.alert = alert

            val greaterOrLessText = itemView.findViewById(R.id.greaterOrLess) as TextView
            val triggerText = itemView.findViewById(R.id.trigger) as TextView
            val recurringText = itemView.findViewById(R.id.recurring) as TextView
            val toggleButton = itemView.findViewById(R.id.toggleButton) as SwitchCompat
            val divider = itemView.findViewById(R.id.divider) as View
            
            greaterOrLessText.text = when {
                alert.trigger.type == Type.CHANGE -> when {
                    alert.trigger.positive -> "▲ Increase of"
                    else -> "▼ Decrease of"
                }
                else -> when {
                    alert.trigger.positive -> "▲ Greater than"
                    else -> "▼ Less than"
                }
            }
            triggerText.text = buildTriggerText()
            recurringText.text = when {
                alert.recurring -> "Recurring"
                else -> "1 time"
            }
            toggleButton.isChecked = when {
                alert.active -> true
                else -> false
            }
            
            divider.visibility = when(dividerVisible) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }

        private fun buildTriggerText(): String {
            val trigger = alert.trigger
            var returnText = ""

            if (trigger.type == Type.CHANGE) {
                returnText += FloatToCurrencyFormatter(alert.trigger.triggerAmount)
                        .formatToTwoDecimals()
                
                return "$returnText%"
            } else {
                returnText += FloatToCurrencyFormatter(alert.trigger.triggerAmount)
                        .formatWithDollarSign()
                
                return returnText
            }
        }
    }
}