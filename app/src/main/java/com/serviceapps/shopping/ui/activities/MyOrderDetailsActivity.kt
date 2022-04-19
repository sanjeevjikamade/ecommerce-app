package com.serviceapps.shopping.ui.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.serviceapps.shopping.R
import com.serviceapps.shopping.models.Order
import com.serviceapps.shopping.ui.adapters.CartItemsListAdapter
import com.serviceapps.shopping.utils.Constants
import kotlinx.android.synthetic.main.activity_my_order_details.*
import java.text.SimpleDateFormat
import java.util.*

import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.serviceapps.shopping.firestore.FirestoreClass
import com.serviceapps.shopping.models.Address
import com.serviceapps.shopping.models.Cart


/**
 * My Order Details Screen.
 */
class MyOrderDetailsActivity : BaseActivity() {

    var mOrderDetails: Order? = null
    var mMenuItem : MenuItem? = null

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_my_order_details)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        setupActionBar()

        var myOrderDetails: Order = Order()

        if (intent.hasExtra(Constants.EXTRA_MY_ORDER_DETAILS)) {
            myOrderDetails =
                intent.getParcelableExtra<Order>(Constants.EXTRA_MY_ORDER_DETAILS)!!
        }

        setupUI(myOrderDetails)
    }

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

    /**
     * A function to setup UI.
     *
     * @param orderDetails Order details received through intent.
     */
    private fun setupUI(orderDetails: Order) {

        tv_order_details_id.text = orderDetails.title
        mOrderDetails = orderDetails

        // Date Format in which the date will be displayed in the UI.
        val dateFormat = "dd MMM yyyy HH:mm"
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = orderDetails.order_datetime

        val orderDateTime = formatter.format(calendar.time)
        tv_order_details_date.text = orderDateTime

        // Get the difference between the order date time and current date time in hours.
        // If the difference in hours is 1 or less then the order status will be PENDING.
        // If the difference in hours is 2 or greater then 1 then the order status will be PROCESSING.
        // And, if the difference in hours is 3 or greater then the order status will be DELIVERED.

//        val diffInMilliSeconds: Long = System.currentTimeMillis() - orderDetails.order_datetime
//        val diffInHours: Long = TimeUnit.MILLISECONDS.toHours(diffInMilliSeconds)
//        Log.e("Difference in Hours", "$diffInHours")
//
//        when {
//            diffInHours < 1 -> {
//                tv_order_status.text = resources.getString(R.string.order_status_pending)
//                tv_order_status.setTextColor(
//                    ContextCompat.getColor(
//                        this@MyOrderDetailsActivity,
//                        R.color.colorAccent
//                    )
//                )
//            }
//            else -> {
//                tv_order_status.text = resources.getString(R.string.order_status_complete)
//                tv_order_status.setTextColor(
//                    ContextCompat.getColor(
//                        this@MyOrderDetailsActivity,
//                        R.color.colorOrderStatusDelivered
//                    )
//                )
//            }
//        }

        tv_order_status.text = orderDetails.status
        if(orderDetails.status.equals("pending")) {
            tv_order_status.setTextColor(
                ContextCompat.getColor(
                    this@MyOrderDetailsActivity,
                    R.color.colorOrderStatusPending
                )
            )
        }else
            tv_order_status.setTextColor(ContextCompat.getColor(
                this@MyOrderDetailsActivity,
                R.color.colorOrderStatusComplete
            ))

//        tv_order_status.setOnClickListener(View.OnClickListener {
//            if(FirestoreClass().getCurrentUserType(this).equals("seller")
//                && orderDetails.seller_id.equals(FirestoreClass().getCurrentUserID())
//                && !tv_order_status.text.equals("complete"))
//                showDialog("Update Order Status", this, orderDetails)
//        })

        rv_my_order_items_list.layoutManager = LinearLayoutManager(this@MyOrderDetailsActivity)
        rv_my_order_items_list.setHasFixedSize(true)

        val cartListAdapter =
            CartItemsListAdapter(this@MyOrderDetailsActivity, orderDetails.items, false)
        rv_my_order_items_list.adapter = cartListAdapter

        tv_my_order_details_address_type.text = orderDetails.address.type
        tv_my_order_details_full_name.text = orderDetails.address.name
        tv_my_order_details_address.text =
            "${orderDetails.address.address}, ${orderDetails.address.zipCode}"
        tv_my_order_details_additional_note.text = orderDetails.address.additionalNote

        if (orderDetails.address.otherDetails.isNotEmpty()) {
            tv_my_order_details_other_details.visibility = View.VISIBLE
            tv_my_order_details_other_details.text = orderDetails.address.otherDetails
        } else {
            tv_my_order_details_other_details.visibility = View.GONE
        }
        tv_my_order_details_mobile_number.text = orderDetails.address.mobileNumber

        tv_order_details_sub_total.text = orderDetails.sub_total_amount
        tv_order_details_shipping_charge.text = orderDetails.shipping_charge
        tv_order_details_total_amount.text = orderDetails.total_amount
    }

    @SuppressLint("WrongConstant")
    private fun showDialog(title: String, context: MyOrderDetailsActivity, orderDetails: Order) {
        var builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setPositiveButton("complete",
            DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                    showProgressDialog(resources.getString(R.string.please_wait))

                    val orderModel = Order(
                        orderDetails.user_id,
                        orderDetails.seller_id,
                        orderDetails.items,
                        orderDetails.address,
                        orderDetails.title,
                        orderDetails.image,
                        orderDetails.sub_total_amount,
                        orderDetails.shipping_charge,
                        orderDetails.total_amount,
                        orderDetails.order_datetime,
                        orderDetails.id,
                        status = "complete"
                    )

                    FirestoreClass().updateOrderStatus(
                        this@MyOrderDetailsActivity,
                        orderModel,
                        orderDetails.id
                    )

            })

//        builder.setNeutralButton("pending",
//            DialogInterface.OnClickListener { dialog, id ->
//
//                //dialog.cancel();
//            })

        builder.setNegativeButton("Exit",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }

    /**
     * A function to notify the user about the item quantity updated in the cart list.
     */
    fun itemUpdateSuccess() {
        hideProgressDialog()
        tv_order_status.text = "complete"
        tv_order_status.setTextColor(ContextCompat.getColor(
            this@MyOrderDetailsActivity,
            R.color.colorOrderStatusComplete
        ))
        mMenuItem?.setVisible(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_product_menu, menu)
        var menuItem : MenuItem? = null
        menuItem = menu?.findItem(R.id.action_edit_product)

        if(!FirestoreClass().getCurrentUserType(this).equals("seller")
            || !mOrderDetails?.seller_id.equals(FirestoreClass().getCurrentUserID())
            || tv_order_status.text.equals("complete")) {

            menuItem?.setVisible(false)
        }
        mMenuItem = menuItem

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_edit_product) {

            if(FirestoreClass().getCurrentUserType(this).equals("seller")
                && mOrderDetails?.seller_id.equals(FirestoreClass().getCurrentUserID())
                && !tv_order_status.text.equals("complete"))
                mOrderDetails?.let { showDialog("Update Order Status", this, it) }

            return true
        }


        return super.onOptionsItemSelected(item)
    }
}