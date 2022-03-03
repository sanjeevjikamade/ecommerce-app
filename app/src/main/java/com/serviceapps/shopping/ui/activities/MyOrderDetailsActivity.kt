package com.serviceapps.shopping.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serviceapps.shopping.R
import com.serviceapps.shopping.models.Order
import com.serviceapps.shopping.utils.Constants
import kotlinx.android.synthetic.main.activity_my_order_details.*

// TODO Step 1: Create an empty activity as MyOrderDetailsActivity for my order details.
// START
/**
 * My Order Details Screen.
 */
class MyOrderDetailsActivity : AppCompatActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_my_order_details)

        // TODO Step 9: Call the function to setup action bar.
        // START
        setupActionBar()
        // END

        // TODO Step 10: Get the order details through intent.
        // START
        val myOrderDetails: Order
        if (intent.hasExtra(Constants.EXTRA_MY_ORDER_DETAILS)) {
            myOrderDetails =
                intent.getParcelableExtra<Order>(Constants.EXTRA_MY_ORDER_DETAILS)!!
        }
        // END
    }

    // TODO Step 8: Create a function to setup action bar.
    // START
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_my_order_details_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_my_order_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
    // END
}
// END