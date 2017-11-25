package com.waylonbrown.coinaware.dummy

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waylonbrown.coinaware.R
import com.waylonbrown.coinaware.portfolio.PortfolioAdapter

// TODO: remove
class DummyFragment : Fragment() {
    
    lateinit var portfolioAdapter: PortfolioAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.page_dummy, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }

}
