package com.waylonbrown.coinaware.util

import java.text.DecimalFormat

class FloatToCurrencyFormatter(val value: Float) {
    
    fun format(): String {
        return "$${DecimalFormat("#.00").format(value)}"
    }
}