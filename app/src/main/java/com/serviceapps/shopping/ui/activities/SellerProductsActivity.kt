package com.serviceapps.shopping.ui.activities

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.serviceapps.shopping.R
import com.serviceapps.shopping.ui.fragments.CampaignsFragment
import com.serviceapps.shopping.ui.fragments.DashboardFragment
import com.serviceapps.shopping.ui.fragments.ProductsFragment
import com.serviceapps.shopping.utils.Constants
import com.serviceapps.shopping.utils.Constants.EXTRA_PRODUCT_OWNER_ID
import kotlinx.android.synthetic.main.activity_seller_products.*
import android.app.PendingIntent.getActivity
import android.content.pm.PackageManager
import android.view.View

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_product_details.*


class SellerProductsActivity : AppCompatActivity(), View.OnClickListener {

    var mSellerId = ""
    var mSellerPhone = ""
    var mSellerName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_products)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, com.serviceapps.shopping.R.color.colorPrimaryDark))

        mSellerId = intent.getStringExtra(EXTRA_PRODUCT_OWNER_ID).toString()
        mSellerPhone = intent.getStringExtra(Constants.EXTRA_SELLER_PHONE).toString()
        mSellerName = intent.getStringExtra(Constants.EXTRA_SELLER_NAME).toString()

        var text = toolbar.findViewById(R.id.title) as TextView
        text.text = mSellerName
        back_button.setOnClickListener(this)

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
    fun getSellerName(): String? {
        return mSellerName
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {

                // The permission code is similar to the user profile image selection.
                R.id.back_button -> {
                    finish()
                }

            }
        }
    }
}