package com.waylonbrown.coinaware.screens.alerts

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.screens.alerts.AlertTrigger.Type
import com.waylonbrown.coinaware.screens.alerts.AlertsAdapter.AlertItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.util.FloatToCurrencyFormatter

// TODO: remove this comment when done
// Viewholders example: https://jonfhancock.com/your-viewholders-are-dumb-make-em-not-dumb-82e6f73f630c
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
            holder.setData(items[position].header!!)
        } else if (holder is AlertItemViewHolder) {
            // Null check has already happened
            holder.setData(items[position].item!!, isDividerVisibleAtPosition(position))
        }
    }

    private fun isDividerVisibleAtPosition(position: Int): Boolean {
        if (position == items.lastIndex ||
                items[position + 1].isHeader()) {
            return false
        }
        return true
    }

    override fun getItemCount(): Int = items.size

    class AlertHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: need to keep value here? Same for all other holders
        lateinit var header: AlertHeader

        fun setData(header: AlertHeader) {
            this.header = header

            val coinTitle = itemView.findViewById(R.id.coinTitle) as TextView
            val coinPrice = itemView.findViewById(R.id.coinPrice) as TextView
            
            coinTitle.text = header.name
            coinPrice.text = FloatToCurrencyFormatter(header.currentPrice).formatWithDollarSign()
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