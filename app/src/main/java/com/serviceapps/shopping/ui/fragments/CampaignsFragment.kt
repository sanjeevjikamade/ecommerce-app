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
import kotlinx.android.synthetic.main.fragment_campaigns.*
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A products fragment.
 */
class CampaignsFragment : BaseFragment() {

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
        mRootView = inflater.inflate(R.layout.fragment_campaigns, container, false)
        return mRootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_campaign_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val addCampaign = menu.findItem(R.id.action_add_campaign)

        addCampaign.isVisible = getActivity()?.let { FirestoreClass().getCurrentUserType(it) } == "seller"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_add_campaign) {
            startActivity(Intent(activity, AddCampaignActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        getCampaignListFromFireStore()
    }

    private fun getCampaignListFromFireStore() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class.
        FirestoreClass().getCampaignssList(this@CampaignsFragment)
    }

    /**
     * A function to get the successful product list from cloud firestore.
     *
     * @param campaignsList Will receive the product list from cloud firestore.
     */
    fun successCampaignListFromFireStore(campaignsList: ArrayList<Campaign>) {

        // Hide Progress dialog.
        hideProgressDialog()

        if (campaignsList.size > 0) {
            rv_my_campaign_items.visibility = View.VISIBLE
            tv_no_campaigns_found.visibility = View.GONE

            rv_my_campaign_items.layoutManager = LinearLayoutManager(activity)
            rv_my_campaign_items.setHasFixedSize(true)

            val adapterProducts =
                MyCampaignListAdapter(requireActivity(), campaignsList, this@CampaignsFragment)
            rv_my_campaign_items.adapter = adapterProducts
        } else {
            rv_my_campaign_items.visibility = View.GONE
            tv_no_campaigns_found.visibility = View.VISIBLE
        }
    }

    /**
     * A function that will call the delete function of FirestoreClass that will delete the product added by the user.
     *
     * @param productID To specify which product need to be deleted.
     */
    fun deleteCampaign(campaignID: String) {

        showAlertDialogToDeleteCampaign(campaignID)
    }

    /**
     * A function to notify the success result of product deleted from cloud firestore.
     */
    fun campaignDeleteSuccess() {

        // Hide the progress dialog
        hideProgressDialog()

        Toast.makeText(
            requireActivity(),
            resources.getString(R.string.campaign_delete_success_message),
            Toast.LENGTH_SHORT
        ).show()

        // Get the latest campaign list from cloud firestore.
        getCampaignListFromFireStore()
    }
    // END

    /**
     * A function to show the alert dialog for the confirmation of delete campaign from cloud firestore.
     */
    private fun showAlertDialogToDeleteCampaign(campaignID: String) {

        val builder = AlertDialog.Builder(requireActivity())
        //set title for alert dialog
        builder.setTitle(resources.getString(R.string.delete_dialog_title))
        //set message for alert dialog
        builder.setMessage(resources.getString(R.string.delete_campaign_dialog_message))
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton(resources.getString(R.string.yes)) { dialogInterface, _ ->

            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))

            // Call the function of Firestore class.
            FirestoreClass().deleteCampaign(this@CampaignsFragment, campaignID)

            dialogInterface.dismiss()
        }

        //performing negative action
        builder.setNegativeButton(resources.getString(R.string.no)) { dialogInterface, _ ->

            dialogInterface.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    // END
}