package com.waylonbrown.coinaware

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        
        viewPager.adapter = HomeViewPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(HomeViewPageChangeListener(navigation))

        navigation.setOnNavigationItemSelectedListener({ item -> navigationItemSelected(item) })
    }

    private fun navigationItemSelected(item: MenuItem): Boolean {
        val pageType = PageType.of(item.itemId)
        viewPager.setCurrentItem(pageType.ordinal, true)
        return true
    }
    
    private enum class PageType(val id: Int) {
        HOME(R.id.navigation_home),
        DASHBOARD(R.id.navigation_dashboard),
        ALERTS(R.id.navigation_alerts);

        companion object {
            // Given an id, get the enum type
            fun of(id: Int): PageType {
                PageType.values()
                        .filter { it.id == id }
                        .forEach { return it }
                return HOME
            }
        }
    }
    
    class HomeViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        
        override fun getItem(position: Int): Fragment = when (position) {
            0 -> PortfolioFragment()
            1 -> DummyFragment()
            else -> DummyFragment()
        }

        override fun getCount(): Int = PageType.values().size

    }
    
    class HomeViewPageChangeListener(private val navigation: BottomNavigationView) 
            : ViewPager.OnPageChangeListener {
        
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            // Ensure bottom navigation view shows the correct section that's been scrolled to
            when (position) {
                0 -> navigation.menu.findItem(PageType.HOME.id).isChecked = true
                1 -> navigation.menu.findItem(PageType.DASHBOARD.id).isChecked = true
                2 -> navigation.menu.findItem(PageType.ALERTS.id).isChecked = true
            }
        }

    }
}
