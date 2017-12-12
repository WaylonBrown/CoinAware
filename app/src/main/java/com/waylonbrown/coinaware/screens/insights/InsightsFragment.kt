package com.waylonbrown.coinaware.screens.insights

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.waylonbrown.coinaware.base.BaseRecyclerViewFragment
import com.waylonbrown.coinaware.util.DummyInsightsDataProvider
import kotlinx.android.synthetic.main.page_recyclerview.*

class InsightsFragment : BaseRecyclerViewFragment() {
    
    lateinit var insightsAdapter: InsightsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        insightsAdapter = InsightsAdapter(layoutInflater)
        recyclerView.adapter = insightsAdapter
        insightsAdapter.updateItems(DummyInsightsDataProvider().getDummyData())
    }
}
