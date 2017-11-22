package com.waylonbrown.coinaware

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.waylonbrown.coinaware.DummyDataProvider.DummyHeaderListData
import com.waylonbrown.coinaware.PortfolioAdapter.PortfolioHeaderViewHolder
import kotlinx.android.synthetic.main.portfolio_page.*

class PortfolioFragment : Fragment(), PortfolioHeaderViewHolder.ListItemClickedListener {
    
    lateinit var portfolioAdapter: PortfolioAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.portfolio_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        portfolioAdapter = PortfolioAdapter(layoutInflater, this)
        recyclerView.adapter = portfolioAdapter
        portfolioAdapter.updateItems(DummyDataProvider().getDummyHeaderData())
        
    }

    override fun itemClicked(data: DummyHeaderListData) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
    }

}
