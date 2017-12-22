package com.waylonbrown.coinaware.screens.portfolio

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.waylonbrown.coinaware.base.BaseRecyclerViewFragment
import com.waylonbrown.coinaware.screens.portfolio.PortfolioAdapter.PortfolioHeaderViewHolder.ListHeaderClickedListener
import com.waylonbrown.coinaware.screens.portfolio.PortfolioAdapter.PortfolioItemViewHolder.ListItemClickedListener
import com.waylonbrown.coinaware.util.DummyPortfolioDataProvider.PortfolioListItem
import kotlinx.android.synthetic.main.page_recyclerview.*

class PortfolioFragment : BaseRecyclerViewFragment(), 
        ListHeaderClickedListener, 
        ListItemClickedListener,
        Observer<List<PortfolioListItem>>{
    
    private lateinit var viewModel: PortfolioViewModel
    private lateinit var portfolioAdapter: PortfolioAdapter
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        portfolioAdapter = PortfolioAdapter(layoutInflater, this, this)
        recyclerView.adapter = portfolioAdapter
        
        viewModel = ViewModelProviders.of(this).get(PortfolioViewModel::class.java)
        viewModel.initialize()
        // Is set in initialization
        viewModel.listData!!.observe(this, this)
    }

    /**
     * Underlying data has change, have it reflect in the UI
     */
    override fun onChanged(itemList: List<PortfolioListItem>?) {
        if (itemList != null) {
            portfolioAdapter.updateItems(itemList)
        } else {
            Toast.makeText(this@PortfolioFragment.activity, "Couldn't get data",
                    Toast.LENGTH_SHORT).show()
        }
    }

    override fun headerClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Header clicked", Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(data: PortfolioListItem) {
        Toast.makeText(activity, "Item clicked", Toast.LENGTH_SHORT).show()
    }

}
