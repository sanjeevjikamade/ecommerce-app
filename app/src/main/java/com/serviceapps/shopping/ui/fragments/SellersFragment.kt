package com.serviceapps.shopping.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceapps.shopping.models.Product
import com.serviceapps.shopping.ui.adapters.MyProductsListAdapter
import com.serviceapps.shopping.R
import com.serviceapps.shopping.firestore.FirestoreClass
import com.serviceapps.shopping.models.Campaign
import com.serviceapps.shopping.ui.activities.AddCampaignActivity
import com.serviceapps.shopping.ui.activities.AddProductActivity
import com.serviceapps.shopping.ui.adapters.MyCampaignListAdapter
import com.serviceapps.shopping.ui.adapters.sellerListAdapter
import kotlinx.android.synthetic.main.fragment_campaigns.*
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A products fragment.
 */
class SellersFragment : BaseFragment() {

    private lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(R.layout.fragment_sellers, container, false)
        return mRootView
    }

    override fun onResume() {
        super.onResume()

        getCampaignListFromFireStore()
    }

    private fun getCampaignListFromFireStore() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getSellersList(this@SellersFragment)
    }

    /**
     * A function to get the successful product list from cloud firestore.
     *
     * @param campaignsList Will receive the product list from cloud firestore.
     */
    fun successSellersListFromFireStore(campaignsList: ArrayList<Campaign>) {

        // Hide Progress dialog.
        hideProgressDialog()

        if (campaignsList.size > 0) {
            rv_my_campaign_items.visibility = View.VISIBLE
            tv_no_campaigns_found.visibility = View.GONE

            rv_my_campaign_items.layoutManager = LinearLayoutManager(activity)
            rv_my_campaign_items.setHasFixedSize(true)

            val adapterProducts =
                sellerListAdapter(requireActivity(), campaignsList, this@SellersFragment)
            rv_my_campaign_items.adapter = adapterProducts
        } else {
            rv_my_campaign_items.visibility = View.GONE
            tv_no_campaigns_found.visibility = View.VISIBLE
        }
    }

}