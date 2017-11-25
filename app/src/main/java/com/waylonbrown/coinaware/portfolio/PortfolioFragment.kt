package com.waylonbrown.coinaware.portfolio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.dummy.DummyChartDataProvider.PortfolioListItem
import com.waylonbrown.coinaware.portfolio.PortfolioAdapter.PortfolioHeaderViewHolder
import com.waylonbrown.coinaware.dummy.DummyChartDataProvider
import kotlinx.android.synthetic.main.page_recyclerview.*

// TODO: base adapter fragment
class PortfolioFragment : Fragment(), PortfolioHeaderViewHolder.ListItemClickedListener {
    
    lateinit var portfolioAdapter: PortfolioAdapter

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
        portfolioAdapter.updateItems(DummyChartDataProvider().getDummyData())
        
    }

    override fun itemClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
    }

}
