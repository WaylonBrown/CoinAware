package com.waylonbrown.coinaware.insights

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.dummy.DummyInsightsDataProvider
import kotlinx.android.synthetic.main.page_recyclerview.*

// TODO: base adapter fragment
class InsightsFragment : Fragment() {
    
    lateinit var insightsAdapter: InsightsAdapter

    override fun onCreateView(inflater: LayoutInflater, 
                              container: ViewGroup?, 
                              savedInstanceState: Bundle?): View? 
            = inflater.inflate(R.layout.page_recyclerview, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        insightsAdapter = InsightsAdapter(layoutInflater)
        recyclerView.adapter = insightsAdapter
        insightsAdapter.updateItems(DummyInsightsDataProvider().getDummyData())
    }
}
