package com.waylonbrown.coinaware

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.mikephil.charting.data.Entry
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
        portfolioAdapter.updateItems(DummyHeaderListData(getDummyData()))
        
    }

    private fun getDummyData(): List<Entry> {
        val list = mutableListOf<Entry>()
        list.add(Entry(0F, 10F))
        list.add(Entry(1F, 8F))
        list.add(Entry(2F, 7F))
        list.add(Entry(3F, 8F))
        list.add(Entry(4F, 7F))
        list.add(Entry(5F, 7F))
        list.add(Entry(6F, 10F))
        list.add(Entry(7F, 12F))
        list.add(Entry(8F, 13F))
        list.add(Entry(9F, 17F))
        return list
    }

    override fun itemClicked(data: DummyHeaderListData) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
    }

}
