package com.waylonbrown.coinaware.alerts

data class AlertListItem(val header: AlertHeader? = null,
                         val item: Alert? = null) {

    fun isHeader(): Boolean = header != null
}

data class AlertHeader(val name: String, val currentPrice: Float)

data class Alert(val trigger: AlertTrigger,
                 val recurring: Boolean,
                 val active: Boolean)

data class AlertTrigger(val type: Type,
                   val positive: Boolean,
                   val triggerAmount: Float) {

    enum class Type {
        VALUE,
        CHANGE
    }
}