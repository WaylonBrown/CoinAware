package com.waylonbrown.coinaware.features.alerts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.waylonbrown.coinaware.features.alerts.AlertsAdapter.AlertItemViewHolder
import com.waylonbrown.coinaware.base.BaseRecyclerViewFragment
import com.waylonbrown.coinaware.util.DummyAlertsDataProvider
import kotlinx.android.synthetic.main.page_recyclerview.*

class AlertsFragment : BaseRecyclerViewFragment(), AlertItemViewHolder.ListItemClickedListener {
    
    private lateinit var alertsAdapter: AlertsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        alertsAdapter = AlertsAdapter(layoutInflater, this)
        recyclerView.adapter = alertsAdapter
        alertsAdapter.updateItems(DummyAlertsDataProvider().getDummyData())
    }

    override fun itemClicked(data: Alert) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
    }
}
