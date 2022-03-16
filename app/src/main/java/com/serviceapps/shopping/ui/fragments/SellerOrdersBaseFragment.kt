package com.serviceapps.shopping.ui.fragments

import com.serviceapps.shopping.ui.fragments.BaseFragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.serviceapps.shopping.R
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.serviceapps.shopping.ui.adapters.SellerOrderPagerAdapter
import kotlinx.android.synthetic.main.seller_orders_base_layout.*

class SellerOrdersBaseFragment : BaseFragment() {
    var myFragment: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.seller_orders_base_layout, container, false)
        return myFragment
    }

    //Call onActivity Create method
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPager(viewPager)
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setUpViewPager(viewPager: ViewPager?) {
        val adapter = SellerOrderPagerAdapter(childFragmentManager)
        adapter.addFragment(SellerOrdersFragment(), "Customer Orders")
        adapter.addFragment(OrdersFragment(), "Your Orders")
        viewPager!!.adapter = adapter
    }

    companion object {
        val instance: SellerOrdersBaseFragment
            get() = SellerOrdersBaseFragment()
    }
}