package com.serviceapps.shopping.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.serviceapps.shopping.R
import com.serviceapps.shopping.ui.activities.AddProductActivity

/**
 * A products fragment.
 */
class ProductsFragment : Fragment() {

    // TODO Step 4: Override the onCreate function and add the setHasOptionMenu with the value true init. Which is used to create option menu in fragment.
    // START
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    // END

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_products, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        textView.text = "This is Products Fragment"
        return root
    }

    // TODO Step 5: Override the onCreateOptionsMenu function and inflate the Add Product menu.
    // START
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_product_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // END

    // TODO Step 6: Override the onOptionsItemSelected function and handle the actions of items.
    // START
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_add_product) {
            // TODO Step 8: Launch the add product activity.
            // START
            startActivity(Intent(activity, AddProductActivity::class.java))
            // END
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    // END
}