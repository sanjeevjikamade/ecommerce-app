package com.serviceapps.shopping.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serviceapps.shopping.R

// TODO Step 7: Create an empty activity name as AddProductActivity.
// START
/**
 * Add Product screen of the app.
 */
class AddProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
    }
}
// END