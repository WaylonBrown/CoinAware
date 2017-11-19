package com.waylonbrown.coinaware.util

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup

/**
 * Implementation of {@link PagerAdapter} that represents each page as a {@link View}.
 *
 * @author Sebastian Kaspari <sebastian@androidzeitgeist.com>
 */
abstract class ViewPagerAdapter : PagerAdapter() {
    /**
     * Get a View that displays the data at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param pager    The ViewPager that this view will eventually be attached to.
     *
     * @return A View corresponding to the data at the specified position.
     */
    abstract fun getView(position: Int, pager: ViewPager): View

    /**
     * Determines whether a page View is associated with a specific key object as
     * returned by instantiateItem(ViewGroup, int).
     *
     * @param view   Page View to check for association with object
     * @param object Object to check for association with view
     *
     * @return true if view is associated with the key object object.
     */
    override fun isViewFromObject(view: View, obj: Any): Boolean 
            = view == obj

    /**
     * Create the page for the given position.
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     *
     * @return Returns an Object representing the new page. This does not need
     *         to be a View, but can be some other container of the page.
     */
     override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val pager = container as ViewPager
        val view = getView(position, pager)

        pager.addView(view)

        return view
    }

    /**
     * Remove a page for the given position.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param view      The same object that was returned by instantiateItem(View, int).
     */
    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        (container as ViewPager).removeView(view as View)
    }
}