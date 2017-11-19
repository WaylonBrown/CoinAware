package com.waylonbrown.coinaware

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.waylonbrown.coinaware.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    
    private enum class PageType(val id: Int) {
        HOME(R.id.navigation_home),
        DASHBOARD(R.id.navigation_dashboard),
        NOTIFICATIONS(R.id.navigation_notifications)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        
        viewPager.adapter = HomeViewPagerAdapter(this)
        viewPager.addOnPageChangeListener(HomeViewPageChangeListener(navigation))

        navigation.setOnNavigationItemSelectedListener({ item -> navigationItemSelected(item) })
    }

    private fun navigationItemSelected(item: MenuItem): Boolean {
        val index = when (item.itemId) {
            R.id.navigation_home -> 0
            R.id.navigation_dashboard -> 1
            R.id.navigation_notifications -> 2
            else -> return false
        }
        viewPager.setCurrentItem(index, true)
        return true
    }

    class HomeViewPagerAdapter(private val context: Context) : ViewPagerAdapter() {
        
        override fun getView(position: Int, pager: ViewPager): View {
            val layoutInflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) 
                    as LayoutInflater
            return layoutInflator.inflate(R.layout.view_pager_page, null)
        }

        override fun getCount(): Int = PageType.values().size

    }
    
    // TODO: is navigation here a memory leak?
    class HomeViewPageChangeListener(private val navigation: BottomNavigationView) 
            : ViewPager.OnPageChangeListener {
        
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            // Ensure bottom navigation view shows the correct section that's been scrolled to
            when (position) {
                0 -> navigation.menu.findItem(PageType.HOME.id).isChecked = true
                1 -> navigation.menu.findItem(PageType.DASHBOARD.id).isChecked = true
                2 -> navigation.menu.findItem(PageType.NOTIFICATIONS.id).isChecked = true
            }
        }

    }
}
