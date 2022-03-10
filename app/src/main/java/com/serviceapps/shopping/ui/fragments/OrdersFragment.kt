package com.serviceapps.shopping.ui.fragments

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.serviceapps.shopping.R
import com.serviceapps.shopping.firestore.FirestoreClass
import com.serviceapps.shopping.models.Order
import com.serviceapps.shopping.ui.adapters.MyOrdersListAdapter
import kotlinx.android.synthetic.main.fragment_orders.*

/**
 * Order listing fragment.
 */
class OrdersFragment : BaseFragment() {
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()

        getMyOrdersList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    /**
     * A function to get the list of my orders.
     */
    private fun getMyOrdersList() {
        // Show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))
        //TODO: SID Testing
        if(getActivity()?.let { FirestoreClass().getCurrentUserType(it) } == "seller")
            FirestoreClass().getMyOrdersListSeller(this@OrdersFragment)
        else
            FirestoreClass().getMyOrdersList(this@OrdersFragment)
    }

    /**
     * A function to get the success result of the my order list from cloud firestore.
     *
     * @param ordersList List of my orders.
     */
    fun populateOrdersListInUI(ordersList: ArrayList<Order>) {

        // Hide the progress dialog.
        hideProgressDialog()

        if (ordersList.size > 0) {

            rv_my_order_items.visibility = View.VISIBLE
            tv_no_orders_found.visibility = View.GONE

            rv_my_order_items.layoutManager = LinearLayoutManager(activity)
            rv_my_order_items.setHasFixedSize(true)

            val myOrdersAdapter = MyOrdersListAdapter(requireActivity(), ordersList)
            rv_my_order_items.adapter = myOrdersAdapter
        } else {
            rv_my_order_items.visibility = View.GONE
            tv_no_orders_found.visibility = View.VISIBLE
        }
    }
}