package com.waylonbrown.coinaware.portfolio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.data.CoinPriceFetcher
import com.waylonbrown.coinaware.data.DummyPortfolioDataProvider
import com.waylonbrown.coinaware.data.DummyPortfolioDataProvider.PortfolioListItem
import com.waylonbrown.coinaware.portfolio.PortfolioAdapter.PortfolioHeaderViewHolder
import com.waylonbrown.coinaware.retrofit.CryptoCompareService
import kotlinx.android.synthetic.main.page_recyclerview.*

// TODO: base adapter fragment
class PortfolioFragment : Fragment(), PortfolioHeaderViewHolder.ListItemClickedListener {
    
    lateinit var portfolioAdapter: PortfolioAdapter
    lateinit var cryptoService: CryptoCompareService // TODO: dagger
    

    override fun onCreateView(inflater: LayoutInflater, 
                              container: ViewGroup?, 
                              savedInstanceState: Bundle?): View? 
            = inflater.inflate(R.layout.page_recyclerview, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        portfolioAdapter = PortfolioAdapter(layoutInflater, this)
        recyclerView.adapter = portfolioAdapter
//        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
//                (recyclerView.layoutManager as LinearLayoutManager).orientation)
//        dividerItemDecoration.setDrawable(activity.resources.getDrawable())
//        recyclerView.addItemDecoration(dividerItemDecoration)
        portfolioAdapter.updateItems(DummyPortfolioDataProvider().getDummyData())
        
        swipeRefreshLayout.setOnRefreshListener { 
            CoinPriceFetcher()
                    .getFromBTCtoUSDPrice(object : CoinPriceFetcher.FetchCoinPriceListener {
                        override fun coinPriceFetched(price: Float) {
                            onCoinPriceFetch(price)
                            swipeRefreshLayout.isRefreshing = false
                        }

                        override fun onFetchFail() {
                            Toast.makeText(activity, "Failure fetching coin price", 
                                    Toast.LENGTH_SHORT).show()
                            swipeRefreshLayout.isRefreshing = false
                        }
                    })
        }
    }

    fun onCoinPriceFetch(price: Float) {
        Toast.makeText(activity, "$price", Toast.LENGTH_SHORT).show()
    }
    
    override fun itemClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
    }

}
