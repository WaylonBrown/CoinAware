package com.waylonbrown.coinaware.features.portfolio

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.waylonbrown.coinaware.base.BaseRecyclerViewFragment
import com.waylonbrown.coinaware.features.portfolio.PortfolioAdapter.PortfolioHeaderViewHolder.ListHeaderClickedListener
import com.waylonbrown.coinaware.features.portfolio.PortfolioAdapter.PortfolioItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.io.Resource
import kotlinx.android.synthetic.main.page_recyclerview.*
import timber.log.Timber

class PortfolioFragment : BaseRecyclerViewFragment(), 
        ListHeaderClickedListener, 
        ListItemClickedListener,
        Observer<Resource<PortfolioListData>>, 
        SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: PortfolioViewModel
    private lateinit var portfolioAdapter: PortfolioAdapter
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        portfolioAdapter = PortfolioAdapter(layoutInflater, this, this)
        recyclerView.adapter = portfolioAdapter
        
        viewModel = ViewModelProviders.of(this).get(PortfolioViewModel::class.java)
        viewModel.getLiveData().observe(this, this)
        viewModel.getDataFromCache()
        
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    /**
     * Underlying data has change, have it reflect in the UI
     */
    override fun onChanged(data: Resource<PortfolioListData>?) {
        swipeRefreshLayout.isRefreshing = false
        
        if (data?.data != null && data.status == Resource.Status.SUCCESS) {
            portfolioAdapter.updateItems(data.data)
            Timber.i("Updated items in portfolio list")
            return
        }

        if (data == null || data.status == Resource.Status.ERROR) {
            Toast.makeText(this@PortfolioFragment.activity, "Couldn't get latest BTC price",
                    Toast.LENGTH_SHORT).show()
            return
        } 
    }

    /**
     * Pull to refresh
     */
    override fun onRefresh() {
        viewModel.fetchNewData()
    }

    override fun headerClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Header clicked", Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Item clicked", Toast.LENGTH_SHORT).show()
    }

}
