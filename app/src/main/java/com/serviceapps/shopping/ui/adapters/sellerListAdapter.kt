package com.serviceapps.shopping.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serviceapps.shopping.R
import com.serviceapps.shopping.models.Campaign
import com.serviceapps.shopping.models.User
import com.serviceapps.shopping.ui.activities.SellerProductsActivity
import com.serviceapps.shopping.ui.fragments.SellersFragment
import com.serviceapps.shopping.utils.Constants
import com.serviceapps.shopping.utils.GlideLoader
import kotlinx.android.synthetic.main.item_list_layout.view.*
import kotlinx.android.synthetic.main.item_list_layout.view.ib_delete_product
import kotlinx.android.synthetic.main.item_list_layout.view.iv_item_image
import kotlinx.android.synthetic.main.item_list_layout.view.tv_item_name
import kotlinx.android.synthetic.main.item_list_layout_campaigns.view.*

/**
 * A adapter class for products list items.
 */
open class sellerListAdapter(
    private val context: Context,
    private var list: ArrayList<User>,
    private val fragment: SellersFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_layout_sellers,
                parent,
                false
            )
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {

            GlideLoader(context).loadProductPicture(model.image, holder.itemView.iv_item_image)

            holder.itemView.tv_campaign_name.text = model.firstName
//            holder.itemView.tv_campaign_description.text = "${model.description}"
            holder.itemView.tv_campaign_description.text = model.lastName   //storing description in lname for seller

            holder.itemView.setOnClickListener {
                // Launch Product details screen.`
                print("seller_mobile"+model.mobile.toString().toString())
                val intent = Intent(context, SellerProductsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, model.id)
                intent.putExtra(Constants.EXTRA_SELLER_PHONE, model.mobile.toString())
                context.startActivity(intent)
            }
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}