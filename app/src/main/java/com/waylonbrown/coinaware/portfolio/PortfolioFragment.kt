package com.waylonbrown.coinaware.portfolio

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.waylonbrown.coinaware.R
import kotlinx.android.synthetic.main.portfolio_page.*

class PortfolioFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.portfolio_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.adapter = PortfolioAdapter()
    }

    class PortfolioAdapter : Adapter<PortfolioViewHolder>() {
        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: PortfolioViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PortfolioViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class PortfolioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
    }
}