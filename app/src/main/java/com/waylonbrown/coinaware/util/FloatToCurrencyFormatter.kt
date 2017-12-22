package com.waylonbrown.coinaware.util

import java.text.DecimalFormat

class FloatToCurrencyFormatter(private val value: Float) {

    fun formatWithDollarSign(): String {
        return "$${formatToTwoDecimals()}"
    }

    fun formatToTwoDecimals(): String {
        return DecimalFormat("#,###.00").format(value)
    }
}