package com.serviceapps.shopping.ui.activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.serviceapps.shopping.R
import com.serviceapps.shopping.firestore.FirestoreClass

/**
 *  Dashboard Screen of the app.
 */
class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Update the background color of the action bar as per our design requirement.
        supportActionBar!!.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this@DashboardActivity,
                R.color.colorPrimary
            )
        )
        // END

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_seller,
                R.id.navigation_dashboard,
                R.id.navigation_products,
                R.id.navigation_campaigns,
                R.id.navigation_orders
//                R.id.navigation_sold_products
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)

        navView.getMenu().removeItem(R.id.navigation_dashboard);
        if(FirestoreClass().getCurrentUserType(this@DashboardActivity) == "customer") {
            navView.getMenu().removeItem(R.id.navigation_products);
            navView.getMenu().removeItem(R.id.navigation_campaigns);
        }
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}