package com.waylonbrown.coinaware.alerts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.alerts.AlertsAdapter.AlertItemViewHolder
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider
import com.waylonbrown.coinaware.dummy.DummyAlertDataProvider.Alert
import kotlinx.android.synthetic.main.page_recyclerview.*

// TODO: base adapter fragment
class AlertsFragment : Fragment(), AlertItemViewHolder.ListItemClickedListener {
    lateinit var alertsAdapter: AlertsAdapter

    override fun onCreateView(inflater: LayoutInflater, 
                              container: ViewGroup?, 
                              savedInstanceState: Bundle?): View? 
            = inflater.inflate(R.layout.page_recyclerview, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        alertsAdapter = AlertsAdapter(layoutInflater, this)
        recyclerView.adapter = alertsAdapter
        alertsAdapter.updateItems(DummyAlertDataProvider().getDummyData())

    }

    override fun itemClicked(data: Alert) {
        Toast.makeText(activity, "Clicked", Toast.LENGTH_LONG).show()
    }
}
