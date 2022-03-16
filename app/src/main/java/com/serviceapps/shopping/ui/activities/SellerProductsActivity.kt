package com.serviceapps.shopping.ui.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.serviceapps.shopping.R
import com.serviceapps.shopping.ui.fragments.CampaignsFragment
import com.serviceapps.shopping.ui.fragments.DashboardFragment
import com.serviceapps.shopping.ui.fragments.ProductsFragment
import com.serviceapps.shopping.utils.Constants
import com.serviceapps.shopping.utils.Constants.EXTRA_PRODUCT_OWNER_ID
import kotlinx.android.synthetic.main.activity_seller_products.*

class SellerProductsActivity : AppCompatActivity() {

    var mSellerId = ""
    var mSellerPhone = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_products)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))

        mSellerId = intent.getStringExtra(EXTRA_PRODUCT_OWNER_ID).toString()
        mSellerPhone = intent.getStringExtra(Constants.EXTRA_SELLER_PHONE).toString()

        setupViewPager(viewpager)

        tabs!!.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DashboardFragment(), resources.getString(R.string.tab_text_purchase))
        adapter.addFragment(CampaignsFragment(), resources.getString(R.string.tab_text_campaigns))

        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

    fun getSellerId(): String? {
        return mSellerId
    }
    fun getSellerPhone(): String? {
        return mSellerPhone
    }
}