package com.waylonbrown.coinaware.features.portfolio

import com.github.mikephil.charting.data.Entry

data class PortfolioListData(val items: List<PortfolioListItem>)

data class PortfolioListItem(val header: PortfolioListHeader? = null,
                             val item: PortfolioListCoinItem? = null)

data class PortfolioListCoinItem(val ticker: String,
                                 val name: String,
                                 val data: List<Entry>,
                                 val positiveTrend: Boolean)

data class PortfolioListHeader(var portfolioValue: Float,
                               val data: List<Entry>,
                               val positiveTrend: Boolean)